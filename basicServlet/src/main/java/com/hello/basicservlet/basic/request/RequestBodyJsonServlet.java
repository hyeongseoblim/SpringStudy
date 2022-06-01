package com.hello.basicservlet.basic.request;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.hello.basicservlet.basic.HelloData;
import org.springframework.util.StreamUtils;

import javax.servlet.ServletException;
import javax.servlet.ServletInputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.nio.charset.StandardCharsets;

@WebServlet(name="requestBodyJsonServlet",urlPatterns = "/request-json-string")
public class RequestBodyJsonServlet extends HttpServlet {
    private final ObjectMapper objectMapper = new ObjectMapper();
    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        ServletInputStream servletInputStream = req.getInputStream();
        String messageBody = StreamUtils.copyToString(servletInputStream, StandardCharsets.UTF_8);

        System.out.println("message body = "+messageBody);
        HelloData helloData = objectMapper.readValue(messageBody,HelloData.class);
        System.out.println(helloData.getUsername() + "   "+helloData.getAge());
    }
}
