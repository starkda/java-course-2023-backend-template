package edu.java.configuration;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Setter
@Getter
@ConfigurationProperties(prefix = "services.stackoverflow", ignoreUnknownFields = false)
@Configuration
public class Stackoverflow {
    private String url;
    private String api;
}
