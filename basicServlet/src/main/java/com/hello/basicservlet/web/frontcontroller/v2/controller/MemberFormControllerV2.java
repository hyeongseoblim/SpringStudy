package com.hello.basicservlet.web.frontcontroller.v2.controller;

import com.hello.basicservlet.web.frontcontroller.MyView;
import com.hello.basicservlet.web.frontcontroller.v2.ControllerV2;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class MemberFormControllerV2 implements ControllerV2 {

    @Override
    public MyView prosess(HttpServletRequest request, HttpServletResponse response) throws Exception {
        return new MyView("/WEB-INF/views/new-form.jsp");
    }
}
