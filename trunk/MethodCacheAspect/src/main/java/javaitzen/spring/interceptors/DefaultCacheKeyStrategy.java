package javaitzen.spring.interceptors;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.aspectj.lang.ProceedingJoinPoint;

/**
 * Default key generation. Using Hashcodes.
 * 
 * @author Brian Du Preez
 */
public class DefaultCacheKeyStrategy implements CacheKeyStrategy {

    private Logger logger = Logger.getLogger(DefaultCacheKeyStrategy.class.getName());

    /**
     * The invocation.
     */
    private ProceedingJoinPoint pjp;

    /**
     * Constructor.
     */
    public DefaultCacheKeyStrategy() {
    }

    /**
     * Constructor.
     * 
     * @param pjp
     *            the pjp
     */
    public DefaultCacheKeyStrategy(final ProceedingJoinPoint pjp) {
        this.pjp = pjp;
    }

    /**
     * Create Key.
     * 
     * @return the string
     * @throws IllegalStateException
     *             the illegal state exception
     */
    public String generateKey() throws IllegalStateException {

        String targetName = pjp.getSignature().getDeclaringTypeName();
        String methodName = pjp.getSignature().getName();
        Object[] arguments = pjp.getArgs();

        return getCacheKey(targetName, methodName, arguments);
    }

    /**
     * Creates cache key: targetName.methodName.122.12354324.123123...
     * 
     * @param targetName
     *            The class being called
     * @param methodName
     *            The method being called
     * @param arguments
     *            The arguments of the method being called
     * @return cache key
     */
    private String getCacheKey(final String targetName, final String methodName, final Object[] arguments) {
        StringBuilder sb = new StringBuilder();
        sb.append(targetName).append(".").append(methodName);

        for (Object arg : arguments) {
            if (arg instanceof Map<?, ?>) {
                sb.append(expandMap((Map<?, ?>) arg));
            } else if (arg instanceof Collection<?>) {
                sb.append(buildBuffer((Collection<?>) arg));
            } else {
                sb.append(".").append(arg.hashCode());
            }
        }

        logger.log(Level.INFO, "Cache key is: [" + sb.toString() + "]");
        return sb.toString();
    }

    /**
     * Builds a String from the collection, and
     * also does a basic String sort just to ensure that the params are in the same order.
     * 
     * @param values
     *            to expand
     * @return a builder
     */
    private StringBuilder buildBuffer(final Collection<?> values) {
        StringBuilder bob = new StringBuilder();
        Collections.sort((List<?>) values, new Comparator<Object>() {
            public int compare(final Object arg0, final Object arg1) {
                String thing1 = arg0.toString();
                String thing2 = arg1.toString();
                return thing1.compareTo(thing2);
            }
        });
        for(Object o : values){
            if (o != null) {
                bob.append(".").append(o.hashCode());
            }            
        }
        return bob;
    }

    /**
     * Expand a map.
     * 
     * @param args
     *            the args
     * @return a Buffer
     */
    private StringBuilder expandMap(final Map<?, ?> args) {
        if (args == null) {
            return new StringBuilder();
        }
        List<Object> values = new ArrayList<Object>();
        for (Entry<?, ?> entry : args.entrySet()) {
            values.add(entry.hashCode());
        }
        return buildBuffer(values);
    }

    /**
     * Setter.
     * 
     * @param invocation
     *            to set
     */
    public void setObject(final Object invocation) {
        this.pjp = (ProceedingJoinPoint) invocation;
    }

    /**
     * Default *.
     * 
     * @return *
     */
    public String classForStrategy() {
        return "*";
    }
}
