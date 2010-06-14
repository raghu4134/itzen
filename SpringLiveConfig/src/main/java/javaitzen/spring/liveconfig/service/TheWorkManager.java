package javaitzen.spring.liveconfig.service;

import java.util.Date;

import javaitzen.spring.liveconfig.LiveConfig;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

/**
 * The Class ScheduledProcessor.
 */
@Service
public class TheWorkManager implements WorkManager {

    @LiveConfig
    private String filePickupURL;

    @LiveConfig
    private String enableDetail;

    @LiveConfig(configLocation = "duplicate.properties")
    private String allowDuplicates;

    @Autowired
    private Work worker;

    /**
     * process.
     * 
     * @see javaitzen.spring.liveconfig.service.WorkManager#process()
     */
    @Scheduled(fixedDelay = 10000)
    public void process() {
        System.out.println("processing: " + new Date());
        for (int i = 0; i < 3; i++) {
            worker.doWork("" + i, filePickupURL, enableDetail, allowDuplicates);
        }
    }

}