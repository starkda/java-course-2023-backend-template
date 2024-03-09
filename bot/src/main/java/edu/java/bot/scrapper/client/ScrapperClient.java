package edu.java.bot.scrapper.client;

import edu.java.bot.configuration.ApplicationConfig;
import edu.java.bot.exceptions.MalformedUrlException;
import edu.java.bot.repository.Dao.SiteUrl;
import java.util.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;

@Component
@Scope("prototype")
public class ScrapperClient {

    SiteUrl scrapperUrl;

    WebClient webClient;

    Logger logger = Logger.getLogger(ScrapperClient.class.getName());

    public ScrapperClient(SiteUrl scrapperUrl) {
        this.scrapperUrl = validatedUrl(scrapperUrl);
        webClient = WebClient.builder().baseUrl(scrapperUrl.url).build();
    }

    @Autowired
    public ScrapperClient(ApplicationConfig applicationConfig) {
        scrapperUrl = validatedUrl(new SiteUrl(applicationConfig.scrapperUrl));
        webClient = WebClient.builder().baseUrl("http://localhost:8080").build();
    }

    private SiteUrl validatedUrl(SiteUrl siteUrl) {
        if (!siteUrl.url.matches(".+:[0-9]{4}")) {
            throw new MalformedUrlException();
        }
        return siteUrl;
    }

    @SuppressWarnings("checkstyle:MultipleStringLiterals") public void trackUrl(SiteUrl url) {
        String response = webClient.post().uri("url").bodyValue("{\"url\" : \"kek\"}")
            .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
            .exchangeToMono(clientResponse -> clientResponse.bodyToMono(String.class))
            .block();
        logger.info(response);
    }

    public void untrackUrl(SiteUrl url) {
        String response = webClient.method(HttpMethod.DELETE).uri("url").bodyValue("{\"url\" : \"mem\"}")
            .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
            .exchangeToMono(clientResponse -> clientResponse.bodyToMono(String.class))
            .block();
        logger.info(response);
    }

}
