package com.hello.basicservlet.web.frontcontroller.v5;

import com.hello.basicservlet.web.frontcontroller.ModelView;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public interface MyHandlerAdapter {

    /**
     *
     * @param handler
     * @return
     */
    boolean supports(Object handler);

    /**
     *
     * @param request
     * @param response
     * @param Handler
     * @return
     * @throws ServletException
     * @throws IOException
     */
    ModelView handle(HttpServletRequest request, HttpServletResponse response, Object Handler)throws ServletException, IOException;

}
