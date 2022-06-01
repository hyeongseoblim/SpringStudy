package com.hello.basicservlet.web.frontcontroller.v3.controller;

import com.hello.basicservlet.domain.member.Member;
import com.hello.basicservlet.domain.member.MemberRepository;
import com.hello.basicservlet.web.frontcontroller.ModelView;
import com.hello.basicservlet.web.frontcontroller.MyView;
import com.hello.basicservlet.web.frontcontroller.v2.ControllerV2;
import com.hello.basicservlet.web.frontcontroller.v3.ControllerV3;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;

public class MemberSaveControllerV3 implements ControllerV3 {

    private final MemberRepository memberRepository = MemberRepository.getInstance();


    @Override
    public ModelView process(Map<String, String> paramMap) {
        String username = paramMap.get("username");
        int age = Integer.parseInt(paramMap.get("age"));

        Member member = new Member(username, age);

        memberRepository.save(member);

        ModelView mv = new ModelView("save-result");
        mv.getModel().put("member",member);
        return mv;

    }
}
