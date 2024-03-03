package edu.java;

import edu.java.configuration.ApplicationConfig;
import edu.java.client.ClientFactory;
import edu.java.configuration.Github;
import edu.java.configuration.Stackoverflow;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

@SpringBootApplication
@EnableConfigurationProperties({ApplicationConfig.class, Github.class, Stackoverflow.class})
public class ScrapperApplication {
    @Autowired ApplicationConfig applicationConfig;

    @Autowired ClientFactory clientFactory;

    public static void main(String[] args) {
        SpringApplication.run(ScrapperApplication.class, args);
    }
}
