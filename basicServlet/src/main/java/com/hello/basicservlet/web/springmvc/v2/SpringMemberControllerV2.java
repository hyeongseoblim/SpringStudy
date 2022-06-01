package com.hello.basicservlet.web.springmvc.v2;

import com.hello.basicservlet.domain.member.Member;
import com.hello.basicservlet.domain.member.MemberRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
@RequestMapping("springmvc/v3/members")
public class SpringMemberControllerV2 {

    MemberRepository memberRepository = MemberRepository.getInstance();

    @RequestMapping(value = "/new-form",method = RequestMethod.GET)
    public String newForm(){
        return   "new-form";
    }
    @RequestMapping(value = "/save",method = RequestMethod.POST)
    public String save(@RequestParam("username") String username, @RequestParam("age")int age, Model model){
        Member member = new Member(username,age);
        memberRepository.save(member);
        model.addAttribute("member",member);
        return "save-result";
    }
    @RequestMapping(value = "/members",method = RequestMethod.GET)
    public String members(Model model){
        List<Member> members = memberRepository.findAll();
        model.addAttribute("members",members);
       return "members";
    }

}
