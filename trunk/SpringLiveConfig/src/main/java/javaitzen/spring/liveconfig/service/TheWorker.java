/*
 * 
 */
package javaitzen.spring.liveconfig.service;

import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

/**
 * The Class TheSlaveImpl.
 */
@Component
public class TheWorker implements Work {

    /**
     * work.
     * 
     * @param values
     *            the values
     * @see javaitzen.spring.liveconfig.service.Work#work(int)
     */
    @Override
    @Async
    public void doWork(final String... values) {
        try {
            System.out.println("Live Settings:" + values[0]);
            System.out.println("filePickupURL: " + values[1]);
            System.out.println("enableDetail: " + values[2]);
            System.out.println("allowDuplicates: " + values[3]);
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }

}
