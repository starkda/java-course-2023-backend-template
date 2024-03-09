package edu.java.client;

import edu.java.configuration.ApplicationConfig;
import edu.java.dto.SiteUrl;
import java.util.logging.Logger;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.client.WebClient;

public class BotClient {
    SiteUrl botUrl;

    WebClient webClient;

    Logger logger = Logger.getLogger(BotClient.class.getName());

    public BotClient(ApplicationConfig applicationConfig) {
        SiteUrl siteUrl = new SiteUrl(applicationConfig.botUrl);
        webClient = WebClient.builder().baseUrl(siteUrl.url).build();
        botUrl = siteUrl;
    }

    public void notifyUpdate(SiteUrl siteUrl) {
        String response = webClient.put().uri("url").bodyValue("{\"url\" : \"kek\"}")
            .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
            .exchangeToMono(clientResponse -> clientResponse.bodyToMono(String.class))
            .block();
        logger.info(response);
    }
}
