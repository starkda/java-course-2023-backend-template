package edu.java.configuration;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;

@Configuration
@RequiredArgsConstructor
public class ClientFactory {
    @Autowired final ApplicationConfig applicationConfig;

    @Bean
    @Scope(value = BeanDefinition.SCOPE_PROTOTYPE)
    public GithubRepositoryClient getSimpleRepositoryClient(
        Github github
    ) {
        return new GithubRepositoryClient(github);
    }

    @Bean
    Github github() {
        return applicationConfig.github;
    }

    @Bean
    @Scope(value = BeanDefinition.SCOPE_PROTOTYPE)
    public StackOverflowClient getStackOverflowClient(Stackoverflow stackoverflow) {
        return new StackOverflowClient(stackoverflow);
    }

    @Bean
    Stackoverflow stackoverflow() {
        return applicationConfig.stackoverflow;
    }
}
