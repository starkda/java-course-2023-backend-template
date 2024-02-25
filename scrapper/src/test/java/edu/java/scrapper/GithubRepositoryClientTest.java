package edu.java.scrapper;

import com.github.tomakehurst.wiremock.junit5.WireMockExtension;
import edu.java.configuration.ApplicationConfig;
import edu.java.configuration.ClientFactory;
import edu.java.configuration.Github;
import edu.java.configuration.GithubRepositoryClient;
import edu.java.configuration.StackOverflowClient;
import edu.java.dto.SiteUrl;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.extension.RegisterExtension;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.web.reactive.function.client.WebClient;
import static com.github.tomakehurst.wiremock.client.WireMock.aResponse;
import static com.github.tomakehurst.wiremock.client.WireMock.get;
import static com.github.tomakehurst.wiremock.client.WireMock.urlPathMatching;
import static com.github.tomakehurst.wiremock.core.WireMockConfiguration.wireMockConfig;

@SpringBootTest(classes = {
    edu.java.configuration.GithubRepositoryClient.class, edu.java.configuration.ApplicationConfig.class,
    StackOverflowClient.class, ApplicationConfig.class, ClientFactory.class})
@EnableConfigurationProperties(ApplicationConfig.class)
public class GithubRepositoryClientTest {

    @Autowired
    org.springframework.context.ApplicationContext applicationContext;
    @Autowired
    GithubRepositoryClient githubRepositoryClient;

    @RegisterExtension
    static WireMockExtension wm1 = WireMockExtension.newInstance()
        .options(wireMockConfig().port(1111))
        .build();

    @ParameterizedTest
    @ValueSource(strings = {"src/test/resources/it/githubRepositoryResponseBody"})
    public void clientReturnsExpectedId(String path) throws IOException {
        String body = readBody(path);
        wm1.stubFor(get(urlPathMatching("/starkda/S24-core-course-labs"))
            .willReturn(aResponse()
                .withStatus(200)
                .withHeader("Content-Type", "application/json")
                .withBody(body)));

        githubRepositoryClient =
            (GithubRepositoryClient) applicationContext.getBean(
                "getSimpleRepositoryClient",
                new Github(wm1.baseUrl(), wm1.baseUrl())
            );

        WebClient webClient = WebClient.builder().baseUrl(wm1.baseUrl()).build();
        String jsonString = webClient.get()
            .uri("/starkda/S24-core-course-labs")
            .exchangeToMono(clientResponse -> clientResponse.bodyToMono(String.class))
            .block();

        Assertions.assertEquals(githubRepositoryClient.fetchRepositoryInfo(new SiteUrl(
            wm1.baseUrl() + "/starkda/S24-core-course-labs")).getId(), 228);
    }

    String readBody(String path) throws IOException {
        return Files.readAllLines(Paths.get(path)).stream().reduce((x, y) -> x + y).get();
    }
}
