package javaitzen.spring.interceptors;

import static org.junit.Assert.assertEquals;

import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * The Class TestAspectCache.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "applicationContext-hazelcast.xml" })
public class TestHazelcastCacheAspect {
    private Logger logger = Logger.getAnonymousLogger();
    
    @Autowired
    private MyLittleTestObject hazelTestObject;

    
    /**
     * Check aspect.
     */
    @Test
    public void checkHazelcastAspect() {

        long starttime = new Date().getTime();
        String returnstr = hazelTestObject.theOtherBusinessMethod("test");
        logger.log(Level.INFO, "Ran the Hazelcast method: " + returnstr);
        long donetime = new Date().getTime();
        long totalTime1 = donetime - starttime;
        logger.log(Level.INFO, "theOtherBusinessMethod()> 1 done - time:" + totalTime1);

        assertEquals(returnstr, "Returning from other business method with value: test 1000000");

    }

    /**
     * Cached time test. Times out after 5 milliseconds, meaning that the cache
     * didn't work.
     */
    @Test(timeout = 5)
    public void cachedHazelcastTimeTest() {
        long starttime = new Date().getTime();
        String returnstr = hazelTestObject.theOtherBusinessMethod("test");
        logger.log(Level.INFO, "Ran the Hazelcast method: " + returnstr);
        long donetime = new Date().getTime();
        long totalTime1 = donetime - starttime;
        logger.log(Level.INFO, "theOtherBusinessMethod()> 2 done - time:" + totalTime1);
        // check that we got the same result with actually entering the
        // function.
        assertEquals(returnstr, "Returning from other business method with value: test 1000000");

    }

}
