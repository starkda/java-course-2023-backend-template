package edu.java.schedule;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

@EnableScheduling
@Configuration
public class LinkUpdaterScheduler {
    Logger logger = LoggerFactory.getLogger(Logger.class);

    @Scheduled(fixedDelayString = "#{@applicationConfig.scheduler.interval}")
    public void update() {
        logger.info("Fixed rate task - " + System.currentTimeMillis());
    }
}
