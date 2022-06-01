package com.hello.basicservlet.web.frontcontroller.v2.controller;

import com.hello.basicservlet.domain.member.Member;
import com.hello.basicservlet.domain.member.MemberRepository;
import com.hello.basicservlet.web.frontcontroller.MyView;
import com.hello.basicservlet.web.frontcontroller.v1.ControllerV1;
import com.hello.basicservlet.web.frontcontroller.v2.ControllerV2;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public class MemberListControllerV2 implements ControllerV2 {

    private final MemberRepository memberRepository = MemberRepository.getInstance();
    @Override
    public MyView prosess(HttpServletRequest request, HttpServletResponse response) throws Exception {
        List<Member> members = memberRepository.findAll();
        request.setAttribute("members",members);
        return new MyView( "/WEB-INF/views/members.jsp");

    }
}
