package com.hello.basicservlet.basic.response;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.hello.basicservlet.basic.HelloData;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "responseApiServlet", urlPatterns = "/response-api")
public class ResponseApiServlet extends HttpServlet {
    private final ObjectMapper objectMapper = new ObjectMapper();

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //Content-Type = application/json
        resp.setContentType("application/json");
        resp.setCharacterEncoding("utf-8");

        HelloData helloData = new HelloData();
        helloData.setAge(20);
        helloData.setUsername("hio");

        String result = objectMapper.writeValueAsString(helloData);
        resp.getWriter().write(result);

    }
}
