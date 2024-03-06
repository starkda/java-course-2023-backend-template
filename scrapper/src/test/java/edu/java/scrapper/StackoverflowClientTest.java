package edu.java.scrapper;

import com.github.tomakehurst.wiremock.junit5.WireMockExtension;
import edu.java.client.GithubRepositoryClient;
import edu.java.configuration.ApplicationConfig;
import edu.java.client.ClientFactory;
import edu.java.configuration.Github;
import edu.java.client.StackOverflowClient;
import edu.java.configuration.Stackoverflow;
import edu.java.dto.SiteUrl;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.extension.RegisterExtension;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.test.context.SpringBootTest;
import static com.github.tomakehurst.wiremock.client.WireMock.aResponse;
import static com.github.tomakehurst.wiremock.client.WireMock.get;
import static com.github.tomakehurst.wiremock.core.WireMockConfiguration.wireMockConfig;

@SpringBootTest(classes = {
    GithubRepositoryClient.class, edu.java.configuration.ApplicationConfig.class,
    StackOverflowClient.class, ApplicationConfig.class, ClientFactory.class, Github.class, Stackoverflow.class})
@EnableConfigurationProperties({ApplicationConfig.class, Github.class, Stackoverflow.class})
public class StackoverflowClientTest {

    @Autowired
    org.springframework.context.ApplicationContext applicationContext;
    @Autowired
    StackOverflowClient stackOverflowClient;

    @RegisterExtension
    static WireMockExtension wm1 = WireMockExtension.newInstance()
        .options(wireMockConfig().port(1112))
        .build();

    @ParameterizedTest
    @ValueSource(strings = {"src/test/resources/it/stackoverflowResponseBody"})
    public void lastActivityDateTest(String path) throws IOException {
        String body = readBody(path);
        wm1.stubFor(get("/questions/66696828/answers?site=stackoverflow")
            .willReturn(aResponse()
                .withStatus(200)
                .withHeader("Content-Type", "application/json")
                .withBody(body)));

        Stackoverflow stackoverflow = new Stackoverflow();
        stackoverflow.setApi(wm1.baseUrl());
        stackoverflow.setUrl(wm1.baseUrl());

        stackOverflowClient =
            (StackOverflowClient) applicationContext.getBean(
                "getStackOverflowClient",
                stackoverflow
            );

        Assertions.assertThat(stackOverflowClient.fetchAnswersInfo(new SiteUrl(
            wm1.baseUrl() + "/questions/66696828")).getLastActivityDate().toString()).isEqualTo("2023-08-07T14:33:47Z");
    }

    String readBody(String path) throws IOException {
        return Files.readAllLines(Paths.get(path)).stream().reduce((x, y) -> x + y).get();
    }
}
