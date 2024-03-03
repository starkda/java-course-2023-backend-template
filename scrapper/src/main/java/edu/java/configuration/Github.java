package edu.java.configuration;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Setter
@Getter
@ConfigurationProperties(prefix = "services.github", ignoreUnknownFields = false)
@Configuration
public class Github {
    private String url;
    private String api;
}
