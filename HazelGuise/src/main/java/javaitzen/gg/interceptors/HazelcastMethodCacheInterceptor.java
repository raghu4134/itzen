package javaitzen.gg.interceptors;

import java.io.Serializable;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.util.LinkedList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;

import com.google.inject.BindingAnnotation;
import com.google.inject.Inject;
import com.hazelcast.core.HazelcastInstance;
import com.hazelcast.core.IMap;
import com.hazelcast.core.MapEntry;


public class HazelcastMethodCacheInterceptor implements MethodInterceptor {


    private Logger logger = Logger.getLogger(HazelcastMethodCacheInterceptor.class.getName());

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


    @Override
    public Object invoke(MethodInvocation invocation) throws Throwable {

        Object[] arguments = invocation.getArguments();
        Object result;
        StringBuilder cacheKey = new StringBuilder();
        defaultKeyStrat = new DefaultCacheKeyStrategy(invocation);

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
            result = invocation.proceed();
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
     * Sets the hazelcast instance.
     * 
     * @param hazelcastInstance
     *            the new hazelcast instance
     */
    @Inject
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
    @Inject
    public final void setCacheName(@CacheName String cacheName) {
        this.cacheName = cacheName;
    }

    @Retention(RetentionPolicy.RUNTIME)
    @Target({ElementType.FIELD, ElementType.PARAMETER})
    @BindingAnnotation
    public @interface CacheName {}

    
}
