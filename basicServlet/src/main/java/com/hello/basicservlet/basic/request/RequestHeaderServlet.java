package com.hello.basicservlet.basic.request;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Enumeration;

@WebServlet(name = "requestHeaderServlet", urlPatterns = "/request-param")
public class RequestHeaderServlet extends HttpServlet {

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//        printHeader(req);
        parsingParameterByGet(req);
    }

    private void parsingParameterByGet(HttpServletRequest request){
        Enumeration<String> params = request.getParameterNames();
        String username = request.getParameter("username");
        System.out.println("test --- username" + username);
        System.out.println("== Params Name ==");
        request.getParameterNames().asIterator().forEachRemaining(
                System.out :: println
        );

    }

    private void printHeader(HttpServletRequest request){
        System.out.println("HEADER_START");
        Enumeration<String> headerNames = request.getHeaderNames();
        while (headerNames.hasMoreElements()){
            String headerName = headerNames.nextElement();
            System.out.println(headerName);
        }
        request.getHeaderNames().asIterator().forEachRemaining(
                System.out::println
        );

        System.out.println("Cookie === print");
        if(request.getCookies() != null){
            for (Cookie cookie : request.getCookies()){
                System.out.println(cookie.getName() + " : "+ cookie.getValue());
            }
        }
        System.out.println();

    }
}
