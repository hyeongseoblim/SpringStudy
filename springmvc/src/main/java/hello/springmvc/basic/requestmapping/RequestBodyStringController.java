package hello.springmvc.basic.requestmapping;

import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.StreamUtils;
import org.springframework.web.bind.annotation.*;

import javax.servlet.ServletInputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.nio.charset.StandardCharsets;

@RestController
public class RequestBodyStringController {
    public Logger log = LoggerFactory.getLogger(getClass());
    @PostMapping("request-body-string-v1")
    public void requestBodyString(HttpServletRequest request, HttpServletResponse response) throws IOException {
        ServletInputStream servletInputStream = request.getInputStream();
        String messageBody = StreamUtils.copyToString(servletInputStream, StandardCharsets.UTF_8);
        log.info(messageBody);
        response.getWriter().write("ok");
    }
}
