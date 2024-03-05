package com.backend.show.scheduler;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class HealthCheckScheduler {

    private static final Logger LOGGER = LoggerFactory.getLogger(HealthCheckScheduler.class);

    @Value("${health.check.cron:false}")
    private Boolean healthCheckSchedulerEnabled;

    @Scheduled(cron = "0 0/30 * 1/1 * ?")
    public void scheduleHealthCheck(){
        if (!healthCheckSchedulerEnabled){
            LOGGER.info("Health-Check scheduler Disabled");
            return;
        }
        LOGGER.info("Health-Check scheduler Enabled");

    }
}
