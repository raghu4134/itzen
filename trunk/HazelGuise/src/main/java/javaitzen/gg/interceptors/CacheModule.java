package javaitzen.gg.interceptors;

import com.google.inject.AbstractModule;
import com.google.inject.Provider;
import com.google.inject.matcher.Matchers;
import com.hazelcast.core.Hazelcast;
import com.hazelcast.core.HazelcastInstance;

/**
 * The Class CacheModule.
 * @author Brian Du Preez
 */
public class CacheModule extends AbstractModule{
    
    /* (non-Javadoc)
     * @see com.google.inject.AbstractModule#configure()
     */
    protected void configure() {
        HazelcastMethodCacheInterceptor cache = new HazelcastMethodCacheInterceptor();
        requestInjection(cache);
        
        //Neat way to do factories
        bind(HazelcastInstance.class).toProvider(new Provider<HazelcastInstance>() {
                @Override
                public HazelcastInstance get() {
                   return Hazelcast.getDefaultInstance();
                }
        });
        bind(CacheKeyStrategy.class).to(DefaultCacheKeyStrategy.class);
        bindConstant().annotatedWith(HazelcastMethodCacheInterceptor.CacheName.class).to("testCache");
        bindInterceptor(Matchers.only(MyLittleTestObject.class), Matchers.annotatedWith(Cachable.class), cache);
        
    }

}
