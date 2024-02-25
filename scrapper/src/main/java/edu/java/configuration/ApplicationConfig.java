package edu.java.configuration;

import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Setter
@ConfigurationProperties(prefix = "app", ignoreUnknownFields = false)
@Configuration
public class ApplicationConfig {
    public Github github;
    public Stackoverflow stackoverflow;
}

