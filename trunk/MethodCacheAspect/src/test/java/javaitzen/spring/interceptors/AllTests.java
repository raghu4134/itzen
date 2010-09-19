package javaitzen.spring.interceptors;

import junit.framework.Test;
import junit.framework.TestSuite;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

@RunWith(Suite.class)
@Suite.SuiteClasses( { TestAspectCache.class, TestHazelcastCacheAspect.class })
public class AllTests {

    public static Test suite() {
        TestSuite suite = new TestSuite("Test for javaitzen.spring.interceptors");
        return suite;
    }

}
