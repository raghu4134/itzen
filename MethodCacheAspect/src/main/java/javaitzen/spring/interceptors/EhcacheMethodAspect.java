package javaitzen.spring.interceptors;

import java.io.Serializable;
import java.util.LinkedList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import net.sf.ehcache.Cache;
import net.sf.ehcache.Element;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Aspect;

/**
 * The Class MethodCacheAspect.
 * 
 * @author Brian Du Preez
 */
@Aspect
public class EhcacheMethodAspect {

    private Logger logger = Logger.getLogger(EhcacheMethodAspect.class.getName());

    /**
     * The cache.
     */
    private Cache cache;

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
            logger.log(Level.INFO, "Have a Key Strategy to use...");
            for (CacheKeyStrategy strat : keyStrategies) {
                if ((arguments != null) && (arguments.length != 0)) {
                    logger.log(Level.INFO, "Have Arguments...");
                    for (Object arg : arguments) {
                        logger.log(Level.INFO, "Class for Strategy: " + strat.classForStrategy());
                        logger.log(Level.INFO, "Arguments: " + arg);
                        if (Class.forName(strat.classForStrategy()).isInstance(arg)) {
                            strat.setObject(arg);
                            logger.log(Level.INFO, "Using Strategy...");
                            cacheKey.append(strat.generateKey());
                        }
                    }
                }
            }
        }

        if (cacheKey.length() == 0) {
            logger.log(Level.INFO, "Using Default...");
            cacheKey.append(defaultKeyStrat.generateKey());
        }
        Element element = cache.get(cacheKey.toString());

        // not in cache
        if (element == null) {
            result = pjp.proceed();
            if (result != null && !(result instanceof Serializable)) {
                throw new RuntimeException("[" + result.getClass().getName() + "] is not Serializable");
            }
            logger.log(Level.INFO, ">>> caching result - " + cacheKey);
            element = new Element(cacheKey.toString(), (Serializable) result);
            cache.put(element);
        } else {
            logger.log(Level.INFO, ">>> returning result from cache");
            return element.getValue();
        }

        return result;
    }

    /**
     * Generated Getter.
     * 
     * @return the cache
     */
    public Cache getCache() {
        return cache;
    }

    /**
     * Generated Setter.
     * 
     * @param cache
     *            the cache to set
     */
    public void setCache(final Cache cache) {
        this.cache = cache;
    }

}
