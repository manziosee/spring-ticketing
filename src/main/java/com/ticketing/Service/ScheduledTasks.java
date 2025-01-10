package com.ticketing.Service;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class ScheduledTasks {

    @Scheduled(fixedRate = 60000) // Runs every minute
    public void performTask() {
        System.out.println("Scheduled task executed");
    }
}