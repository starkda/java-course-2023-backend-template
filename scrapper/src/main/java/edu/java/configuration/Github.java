package edu.java.configuration;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Setter
@Getter
@ConfigurationProperties(prefix = "services.github", ignoreUnknownFields = false)
@Configuration
public class Github {
    private String url;
    private String api;
}
