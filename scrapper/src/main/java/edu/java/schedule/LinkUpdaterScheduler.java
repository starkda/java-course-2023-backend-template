package edu.java.schedule;

import edu.java.configuration.ApplicationConfig;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

@EnableScheduling
@Configuration
public class LinkUpdaterScheduler {
    Logger logger = LoggerFactory.getLogger(Logger.class);

    @Scheduled(fixedDelayString = "#{@applicationConfig.scheduler.interval}")
    public void update() {
        logger.info("Fixed rate task - " + System.currentTimeMillis() / 1000);
    }
}
