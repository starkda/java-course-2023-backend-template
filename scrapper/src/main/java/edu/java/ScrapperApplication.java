package edu.java;

import edu.java.configuration.ApplicationConfig;
import edu.java.configuration.ClientFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

@SpringBootApplication
@EnableConfigurationProperties(ApplicationConfig.class)
public class ScrapperApplication {
    @Autowired ApplicationConfig applicationConfig;

    @Autowired ClientFactory clientFactory;

    public static void main(String[] args) {
        SpringApplication.run(ScrapperApplication.class, args);
    }
}
