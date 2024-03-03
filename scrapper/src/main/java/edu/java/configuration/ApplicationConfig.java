package edu.java.configuration;

import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import java.time.Duration;

@Setter
@ConfigurationProperties(prefix = "app", ignoreUnknownFields = false)
@Configuration
public class ApplicationConfig {
    public Scheduler scheduler;

    @Setter static
    class Scheduler {
        public boolean enable;
        public Duration interval;
        public Duration forceCheckDelay;
    }

}


