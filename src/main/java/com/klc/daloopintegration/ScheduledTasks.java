package com.klc.daloopintegration;

import com.klc.daloopintegration.services.ConnectivityService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component

public class ScheduledTasks {
    private static final Logger logger = LoggerFactory.getLogger(ScheduledTasks.class);

    @Autowired
    private ConnectivityService connectivityService;



    //Job runs every day at 2AM
    @Scheduled(cron = "0 0 2 * * ?")
    public void performTask() {
        logger.info("Starts job ");

        this.connectivityService.validateStationsIssues();

        logger.info("Ends job");


    }
}
