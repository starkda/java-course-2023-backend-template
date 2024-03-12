package edu.java.bot;

import com.specs.openapi.model.BotPutUrl;
import edu.java.bot.configuration.ApplicationConfig;
import edu.java.bot.web.RestResponseEntityExceptionHandler;
import edu.java.bot.web.UrlController;
import java.net.URISyntaxException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;

@SpringBootTest(classes = {
    UrlController.class, RestResponseEntityExceptionHandler.class})
@EnableConfigurationProperties(ApplicationConfig.class)
public class BotControllerTest {

    @Autowired
    UrlController urlController;

    @Test
    public void sendPost() {
        BotPutUrl postUrl = new BotPutUrl("google.com");
        Assertions.assertEquals(urlController.urlPut(postUrl).getStatusCode(), HttpStatus.OK);
    }

    @Test
    public void sendDelete() {
        BotPutUrl postUrl = new BotPutUrl("google. com");
        Assertions.assertThrows(URISyntaxException.class, () -> urlController.urlPut(postUrl).getStatusCode());
    }
}
