package com.hello.basicservlet.web.frontcontroller.v2;

import com.hello.basicservlet.web.frontcontroller.MyView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public interface ControllerV2 {
    MyView prosess(HttpServletRequest request, HttpServletResponse response) throws Exception;
}
