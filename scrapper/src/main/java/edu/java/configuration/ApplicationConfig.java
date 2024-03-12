package edu.java.configuration;

import java.time.Duration;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Setter
@ConfigurationProperties(prefix = "app", ignoreUnknownFields = false)
@Configuration
public class ApplicationConfig {
    public Scheduler scheduler;
    public String botUrl;

    @Setter
    static class Scheduler {
        public boolean enable;
        public Duration interval;
        public Duration forceCheckDelay;
    }

}


