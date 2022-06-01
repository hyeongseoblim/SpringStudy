package com.hello.basicservlet.web.frontcontroller.v2.controller;

import com.hello.basicservlet.domain.member.Member;
import com.hello.basicservlet.domain.member.MemberRepository;
import com.hello.basicservlet.web.frontcontroller.MyView;
import com.hello.basicservlet.web.frontcontroller.v2.ControllerV2;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class MemberSaveControllerV2 implements ControllerV2 {

    private final MemberRepository memberRepository = MemberRepository.getInstance();

    @Override
    public MyView prosess(HttpServletRequest request, HttpServletResponse response) throws Exception {
        String username = request.getParameter("username");
        int age = Integer.parseInt(request.getParameter("age"));

        Member member = new Member(username, age);

        memberRepository.save(member);

        request.setAttribute("member", member);
        return new MyView("/WEB-INF/views/save-result.jsp");
    }
}
