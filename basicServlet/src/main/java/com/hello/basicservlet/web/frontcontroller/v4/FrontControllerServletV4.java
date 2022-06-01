package com.hello.basicservlet.web.frontcontroller.v4;

import com.hello.basicservlet.web.frontcontroller.ModelView;
import com.hello.basicservlet.web.frontcontroller.MyView;
import com.hello.basicservlet.web.frontcontroller.v4.ControllerV4;
import com.hello.basicservlet.web.frontcontroller.v4.controller.MemberFormControllerV4;
import com.hello.basicservlet.web.frontcontroller.v4.controller.MemberListControllerV4;
import com.hello.basicservlet.web.frontcontroller.v4.controller.MemberSaveControllerV4;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;

@WebServlet(name = "frontControllerServletV4", urlPatterns = "/front-controller/v4/*")
public class FrontControllerServletV4 extends HttpServlet {

    private final Map<String, ControllerV4> controllerV4Map = new HashMap<>();

    //서블릿 생성시 url 패턴에 맞게 컨트롤러 등록
    public FrontControllerServletV4() {
        controllerV4Map.put("/front-controller/v4/members/new-form", new MemberFormControllerV4());
        controllerV4Map.put("/front-controller/v4/members/save", new MemberSaveControllerV4());
        controllerV4Map.put("/front-controller/v4/members", new MemberListControllerV4());
    }

    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) {
        String requestURI = request.getRequestURI();

        ControllerV4 controllerV4 = controllerV4Map.get(requestURI);
        if (controllerV4 == null) {
            response.setStatus(HttpServletResponse.SC_NOT_FOUND);
            return;
        }
        Map<String, String> params = createParamMap(request);
        Map<String, Object> model = new HashMap<>();


        try {
            String viewName = controllerV4.process(params,model);
            MyView view = viewResolver(viewName);
            view.render(model,request, response);
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
