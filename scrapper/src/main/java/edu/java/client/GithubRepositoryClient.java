package edu.java.client;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import edu.java.configuration.Github;
import edu.java.dto.GithubResponse;
import edu.java.dto.SiteUrl;
import edu.java.exceptions.MalformedUrlException;
import org.springframework.web.reactive.function.client.WebClient;

public class GithubRepositoryClient {
    WebClient webClient;
    ObjectMapper mapper;

    String baseUrl;

    String baseApi;

    public GithubRepositoryClient(Github github) {
        this.baseUrl = github.getUrl();
        this.baseApi = github.getApi();
        webClient = WebClient.builder().baseUrl(baseApi).build();
        mapper = new ObjectMapper();
    }

    public GithubResponse fetchRepositoryInfo(SiteUrl siteUrl) throws MalformedUrlException, JsonProcessingException {
        if (!siteUrl.url.matches(baseUrl + "/.+/.+")) {
            throw new MalformedUrlException();
        }
        String url = siteUrl.url.substring(baseUrl.length());

        String jsonResponse = webClient.get()
            .uri(url)
            .exchangeToMono(clientResponse -> clientResponse.bodyToMono(String.class))
            .block();

        return mapper.readValue(jsonResponse, GithubResponse.class);
    }

}
