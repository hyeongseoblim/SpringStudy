package com.hello.basicservlet.web.frontcontroller.v2;

import com.hello.basicservlet.web.frontcontroller.MyView;
import com.hello.basicservlet.web.frontcontroller.v2.controller.MemberFormControllerV2;
import com.hello.basicservlet.web.frontcontroller.v2.controller.MemberListControllerV2;
import com.hello.basicservlet.web.frontcontroller.v2.controller.MemberSaveControllerV2;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@WebServlet(name ="frontControllerServletV2", urlPatterns = "/front-controller/v2/*")
public class FrontControllerServletV2 extends HttpServlet{

    private final Map<String, ControllerV2> controllerV2Map = new HashMap<>();

    //서블릿 생성시 url 패턴에 맞게 컨트롤러 등록
    public FrontControllerServletV2(){
        controllerV2Map.put("/front-controller/v2/members/new-form",new MemberFormControllerV2());
        controllerV2Map.put("/front-controller/v2/members/save", new MemberSaveControllerV2());
        controllerV2Map.put("/front-controller/v2/members", new MemberListControllerV2());
    }

    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response){
        String requestURI = request.getRequestURI();

        ControllerV2 controllerV2 = controllerV2Map.get(requestURI);
        if(controllerV2 == null){
            response.setStatus(HttpServletResponse.SC_NOT_FOUND);
            return;
        }
        try {
            MyView myView = controllerV2.prosess(request,response);
            myView.render(request,response);
        } catch (Exception e) {
            e.printStackTrace();
        }


    }
}
