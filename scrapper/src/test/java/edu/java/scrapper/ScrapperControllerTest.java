package edu.java.scrapper;

import com.baeldung.openapi.model.ScrapperDeleteUrl;
import com.baeldung.openapi.model.ScrapperPostUrl;
import edu.java.configuration.ApplicationConfig;
import edu.java.web.RestResponseEntityExceptionHandler;
import edu.java.web.UrlController;
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
public class ScrapperControllerTest {

    @Autowired
    UrlController urlController;

    @Test
    public void sendPost() {
        ScrapperPostUrl postUrl = new ScrapperPostUrl("google.com");
        ScrapperDeleteUrl deleteUrl = new ScrapperDeleteUrl("google.com");
        Assertions.assertEquals(urlController.urlPost(postUrl).getStatusCode(), HttpStatus.OK);
        Assertions.assertEquals(urlController.urlDelete(deleteUrl).getStatusCode(), HttpStatus.OK);
    }

    @Test
    public void sendDelete() {
        ScrapperPostUrl postUrl = new ScrapperPostUrl("google. com");
        ScrapperDeleteUrl deleteUrl = new ScrapperDeleteUrl("google. com");
        Assertions.assertThrows(URISyntaxException.class, () -> urlController.urlPost(postUrl).getStatusCode());
        Assertions.assertThrows(URISyntaxException.class, () -> urlController.urlDelete(deleteUrl).getStatusCode());
    }
}
