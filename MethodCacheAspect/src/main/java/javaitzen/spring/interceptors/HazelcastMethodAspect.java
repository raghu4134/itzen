package javaitzen.spring.interceptors;

import java.io.Serializable;
import java.util.LinkedList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Aspect;

import com.hazelcast.core.HazelcastInstance;
import com.hazelcast.core.IMap;
import com.hazelcast.core.MapEntry;

/**
 * The Class HazelcastMethodAspect.
 * 
 * @author Brian Du Preez
 */
@Aspect
public class HazelcastMethodAspect {

    private Logger logger = Logger.getLogger(HazelcastMethodAspect.class.getName());

    private HazelcastInstance hazelcastInstance;
    private String cacheName;
    private CacheKeyStrategy defaultKeyStrat;

    private List<CacheKeyStrategy> keyStrategies = new LinkedList<CacheKeyStrategy>();

    /**
     * Sets teh Strategies.
     * 
     * @param cacheKeys
     *            strat
     */
    public void setCacheKeyStrategies(final List<CacheKeyStrategy> cacheKeys) {
        this.keyStrategies = cacheKeys;
    }

    /**
     * Add a Strategies.
     * 
     * @param cacheKey
     *            strat
     */
    public void addCacheKeyStrategy(final CacheKeyStrategy cacheKey) {
        this.keyStrategies.add(cacheKey);
    }

    /**
     * Around advice.
     * 
     * @param pjp
     *            the pjp
     * @return the object
     * @throws Throwable
     *             the throwable
     */
    public Object aroundAdvice(final ProceedingJoinPoint pjp) throws Throwable {

        Object[] arguments = pjp.getArgs();
        Object result;
        StringBuilder cacheKey = new StringBuilder();
        defaultKeyStrat = new DefaultCacheKeyStrategy(pjp);

        if (!keyStrategies.isEmpty()) {
            logger.log(Level.FINE, "Have a Key Strategy to use...");
            for (CacheKeyStrategy strat : keyStrategies) {
                if ((arguments != null) && (arguments.length != 0)) {
                    logger.log(Level.FINE, "Have Arguments...");
                    for (Object arg : arguments) {
                        logger.log(Level.FINE, "Class for Strategy: " + strat.classForStrategy());
                        logger.log(Level.FINE, "Arguments: " + arg);
                        if (Class.forName(strat.classForStrategy()).isInstance(arg)) {
                            strat.setObject(arg);
                            logger.log(Level.FINE, "Using Strategy...");
                            cacheKey.append(strat.generateKey());
                        }
                    }
                }
            }
        }

        if (cacheKey.length() == 0) {
            logger.log(Level.FINE, "Using Default...");
            cacheKey.append(defaultKeyStrat.generateKey());
        }
        MapEntry<String, Serializable> entry = getCache().getMapEntry(cacheKey.toString());
        
        // not in cache
        if (entry == null) {
            result = pjp.proceed();
            if (result != null && !(result instanceof Serializable)) {
                throw new RuntimeException("[" + result.getClass().getName() + "] is not Serializable");
            }
            logger.log(Level.INFO, ">>> caching result - " + cacheKey);
            getCache().put(cacheKey.toString(), (Serializable) result);
        } else {
            logger.log(Level.INFO, ">>> returning result from cache");
            return entry.getValue();
        }

        return result;
    }

    /**
     * Gets the actual cache.
     * 
     * @return the cache
     */
    private IMap<String, Serializable> getCache() {
        return hazelcastInstance.getMap(getCacheName());
    }
    
    
    /**
     * Gets the hazelcast instance.
     * 
     * @return the hazelcast instance
     */
    private final HazelcastInstance getHazelcastInstance() {
        return hazelcastInstance;
    }

    /**
     * Sets the hazelcast instance.
     * 
     * @param hazelcastInstance
     *            the new hazelcast instance
     */
    public final void setHazelcastInstance(HazelcastInstance hazelcastInstance) {
        this.hazelcastInstance = hazelcastInstance;
    }

    /**
     * Gets the cache name.
     * 
     * @return the cache name
     */
    private final String getCacheName() {
        return cacheName;
    }

    /**
     * Sets the cache name.
     * 
     * @param cacheName
     *            the new cache name
     */
    public final void setCacheName(String cacheName) {
        this.cacheName = cacheName;
    }


}
