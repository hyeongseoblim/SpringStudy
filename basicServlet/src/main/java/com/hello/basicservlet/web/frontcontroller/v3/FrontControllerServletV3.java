package com.hello.basicservlet.web.frontcontroller.v3;

import com.hello.basicservlet.web.frontcontroller.ModelView;
import com.hello.basicservlet.web.frontcontroller.MyView;
import com.hello.basicservlet.web.frontcontroller.v3.controller.MemberFormControllerV3;
import com.hello.basicservlet.web.frontcontroller.v3.controller.MemberListControllerV3;
import com.hello.basicservlet.web.frontcontroller.v3.controller.MemberSaveControllerV3;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;

@WebServlet(name = "frontControllerServletV3", urlPatterns = "/front-controller/v3/*")
public class FrontControllerServletV3 extends HttpServlet {

    private final Map<String, ControllerV3> controllerV3Map = new HashMap<>();

    //서블릿 생성시 url 패턴에 맞게 컨트롤러 등록
    public FrontControllerServletV3() {
        controllerV3Map.put("/front-controller/v3/members/new-form", new MemberFormControllerV3());
        controllerV3Map.put("/front-controller/v3/members/save", new MemberSaveControllerV3());
        controllerV3Map.put("/front-controller/v3/members", new MemberListControllerV3());
    }

    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) {
        String requestURI = request.getRequestURI();

        ControllerV3 controllerV3 = controllerV3Map.get(requestURI);
        if (controllerV3 == null) {
            response.setStatus(HttpServletResponse.SC_NOT_FOUND);
            return;
        }
        Map<String, String> params = createParamMap(request);

        try {
            ModelView mv = controllerV3.process(params);
            String viewName = mv.getViewName();
            MyView view = viewResolver(viewName);
            view.render(mv.getModel(),request, response);
        } catch (Exception e) {
            e.printStackTrace();
        }


    }

    private MyView viewResolver(String viewName) {
        return new MyView(String.format("/WEB-INF/views/%s.jsp", viewName));
    }

    private Map<String, String> createParamMap(HttpServletRequest request) {
        Map<String, String> params = new HashMap<>();
        request.getParameterNames().asIterator().forEachRemaining(
                param -> params.put(param, request.getParameter(param))
        );
        return params;
    }
}
