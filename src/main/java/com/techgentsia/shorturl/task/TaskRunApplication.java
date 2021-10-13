package com.techgentsia.shorturl.task;

import com.techgentsia.shorturl.config.ShortUrlProperties;
import com.techgentsia.shorturl.services.BackgroundCheckService;
import org.jobrunr.scheduling.JobScheduler;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import java.util.logging.Logger;

@Component
public class TaskRunApplication implements ApplicationRunner {

    private final BackgroundCheckService backgroundCheckService;

    private final ShortUrlProperties properties;

    private final JobScheduler jobScheduler;

    public static final Logger log = Logger.getLogger(TaskRunApplication.class.getName());

    public TaskRunApplication(BackgroundCheckService backgroundCheckService, ShortUrlProperties properties, JobScheduler jobScheduler) {
        this.backgroundCheckService = backgroundCheckService;
        this.properties = properties;
        this.jobScheduler = jobScheduler;
    }

    @Override
    public void run(ApplicationArguments args) throws Exception {
        log.info("background job started");
        jobScheduler.scheduleRecurrently(String.format("*/%d * * * *",properties.getUrlCheckDelayInMin()),()-> backgroundCheckService.checkUrls());
    }
}