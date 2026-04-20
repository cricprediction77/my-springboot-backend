package com.crictpredict.predictbe.scheduler;

import com.crictpredict.predictbe.service.CounterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class CounterScheduler {

    @Autowired
    private CounterService counterService;

    // Every 1 hour
    @Scheduled(cron = "0 */20 * * * *")
    public void runEveryHour() {
        counterService.updateCounter();
    }

    // Every day at 12:00 AM
    @Scheduled(cron = "0 0 0 * * *")
    public void resetTodayCount() {
        counterService.resetTodayCount();
    }
}