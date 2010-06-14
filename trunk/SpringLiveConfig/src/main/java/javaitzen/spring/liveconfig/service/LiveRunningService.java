package javaitzen.spring.liveconfig.service;

import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * The Class LiveRunningService.
 */
public class LiveRunningService {

    /**
     * The main method.
     * 
     * @param args
     *            the arguments
     */
    public static void main(final String[] args) {
        new ClassPathXmlApplicationContext("service-context.xml", LiveRunningService.class);
    }

}
