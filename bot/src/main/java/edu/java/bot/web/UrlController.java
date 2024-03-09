package edu.java.bot.web;

import com.specs.openapi.api.UrlApi;
import com.specs.openapi.model.BotPutUrl;
import com.specs.openapi.model.BotPutUrl200;
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
    public ResponseEntity<BotPutUrl200> urlPut(
        @Parameter(name = "UrlPutRequest", description = "", required = true) @Valid @RequestBody
        BotPutUrl urlPutRequest
    ) {
        BotPutUrl200 response = new BotPutUrl200();
        String url = urlPutRequest.getUrl();
        response.scrapperResponse(new URI(url));

        return ResponseEntity.ok(response);
    }
}
