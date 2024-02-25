package edu.java.configuration;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import edu.java.dto.Data;
import edu.java.dto.SiteUrl;
import edu.java.dto.StackoverflowResponse;
import edu.java.exceptions.MalformedUrlException;
import java.time.OffsetDateTime;
import java.util.List;
import org.springframework.web.reactive.function.client.WebClient;

public class StackOverflowClient {
    WebClient webClient;
    ObjectMapper mapper;

    String baseUrl;
    String baseApi;

    StackOverflowClient(Stackoverflow stackoverflow) {
        this.baseUrl = stackoverflow.url;
        this.baseApi = stackoverflow.api;
        webClient = WebClient.builder().baseUrl(baseApi).build();
        mapper = new ObjectMapper();
    }

    public StackoverflowResponse fetchAnswersInfo(SiteUrl siteUrl) throws JsonProcessingException {
        if (!siteUrl.url.matches(baseUrl + "/.+/.+")) {
            throw new MalformedUrlException();
        }
        String url = siteUrl.url.substring(baseUrl.length());
        url += "/answers?site=stackoverflow";
        String jsonString = webClient.get()
            .uri(url)
            .exchangeToMono(clientResponse -> clientResponse.bodyToMono(String.class))
            .block();

        Data data = mapper.readValue(jsonString, Data.class);

        List<StackoverflowResponse> stackoverflowResponses = data.getItems().stream()
            .map(item -> new StackoverflowResponse(OffsetDateTime.ofInstant(
                java.time.Instant.ofEpochSecond(item.getLastActivityDate()), java.time.ZoneOffset.UTC)))
            .toList();
        return stackoverflowResponses.get(0);
    }
}
