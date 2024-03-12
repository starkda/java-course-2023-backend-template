package edu.java.web;

import com.baeldung.openapi.api.UrlApi;
import com.baeldung.openapi.model.ScrapperDeleteUrl;
import com.baeldung.openapi.model.ScrapperDeleteUrl200;
import com.baeldung.openapi.model.ScrapperPostUrl;
import com.baeldung.openapi.model.ScrapperPostUrl200;
import io.swagger.v3.oas.annotations.Parameter;
import jakarta.validation.Valid;
import java.net.URI;
import lombok.SneakyThrows;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;

@Controller
public class UrlController implements UrlApi {
    @SneakyThrows @Override
    public ResponseEntity<ScrapperPostUrl200> urlPost(
        @Parameter(name = "UrlPostRequest", description = "", required = true) @Valid @RequestBody
        ScrapperPostUrl urlPostRequest
    ) {
        ScrapperPostUrl200 response = new ScrapperPostUrl200();
        response.scrapperResponse(new URI(urlPostRequest.getUrl()));
        return ResponseEntity.ok(response);
    }

    @SneakyThrows @Override
    public ResponseEntity<ScrapperDeleteUrl200> urlDelete(
        @Parameter(name = "UrlPostRequest", description = "", required = true) @Valid @RequestBody
        ScrapperDeleteUrl urlPostRequest
    ) {
        ScrapperDeleteUrl200 response = new ScrapperDeleteUrl200();
        response.scrapperResponse(new URI(urlPostRequest.getUrl()));
        return ResponseEntity.ok(response);
    }
}
