package hello.springmvc.basic.requestmapping;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.util.StreamUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.ServletInputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.nio.charset.StandardCharsets;


/**
 * {"username":"hello", "age":20}
 * content-type: application/json
 */
@RestController
@Slf4j
public class RequestBodyJsonController {

    private ObjectMapper objectMapper = new ObjectMapper();

    @PostMapping("/request-body-json-v1")
    public void requestBodyJsonV1(HttpServletRequest request, HttpServletResponse response) throws IOException {
        ServletInputStream inputStream = request.getInputStream();
        String messageBody = StreamUtils.copyToString(inputStream, StandardCharsets.UTF_8);

        log.info("MessageBody = {}", messageBody);
        RequestParams requestParams = objectMapper.readValue(messageBody, RequestParams.class);
        log.info("username ={} ", requestParams.getUsername());
        log.info("age ={} ", requestParams.getAge());
    }

    @ResponseBody
    @PostMapping("/request-body-json-v3")
    public RequestParams requestBodyJsonV3(@RequestBody RequestParams requestParams) {
        log.info("username = {}", requestParams.getUsername());
        log.info("age = {}", requestParams.getAge());
        return requestParams;
    }
}
