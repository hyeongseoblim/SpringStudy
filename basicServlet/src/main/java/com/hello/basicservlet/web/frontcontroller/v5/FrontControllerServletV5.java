package com.hello.basicservlet.web.frontcontroller.v5;

import com.hello.basicservlet.web.frontcontroller.ModelView;
import com.hello.basicservlet.web.frontcontroller.MyView;
import com.hello.basicservlet.web.frontcontroller.v3.controller.MemberFormControllerV3;
import com.hello.basicservlet.web.frontcontroller.v3.controller.MemberListControllerV3;
import com.hello.basicservlet.web.frontcontroller.v3.controller.MemberSaveControllerV3;
import com.hello.basicservlet.web.frontcontroller.v4.controller.MemberFormControllerV4;
import com.hello.basicservlet.web.frontcontroller.v4.controller.MemberListControllerV4;
import com.hello.basicservlet.web.frontcontroller.v4.controller.MemberSaveControllerV4;
import com.hello.basicservlet.web.frontcontroller.v5.adapter.ControllerV3HandlerAdapter;
import com.hello.basicservlet.web.frontcontroller.v5.adapter.ControllerV4HandlerAdapter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@WebServlet(name = "frontControllerServletV5", urlPatterns = "/front-controller/v5/*")
public class FrontControllerServletV5 extends HttpServlet {

    private final Map<String, Object> handlerControllerMap = new HashMap<>();
    private final List<MyHandlerAdapter> handlerAdapterList = new ArrayList<>();

    public FrontControllerServletV5() {
        initHandlerMappingMap();
        initHandlerAdapters();
    }

    private void initHandlerAdapters() {
        handlerAdapterList.add(new ControllerV3HandlerAdapter());
        handlerAdapterList.add(new ControllerV4HandlerAdapter());
    }

    private void initHandlerMappingMap() {
        handlerControllerMap.put("/front-controller/v5/v3/members/new-form", new MemberFormControllerV3());
        handlerControllerMap.put("/front-controller/v5/v3/members/save", new MemberSaveControllerV3());
        handlerControllerMap.put("/front-controller/v5/v3/members", new MemberListControllerV3());
        handlerControllerMap.put("/front-controller/v5/v4/members/new-form", new MemberFormControllerV4());
        handlerControllerMap.put("/front-controller/v5/v4/members/save", new MemberSaveControllerV4());
        handlerControllerMap.put("/front-controller/v5/v4/members", new MemberListControllerV4());
    }

    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        Object handler = getHandler(request);

        if (handler == null) {
            response.setStatus(HttpServletResponse.SC_NOT_FOUND);
            return;
        }
        MyHandlerAdapter myHandlerAdapter = getHandlerAdapter(handler);
        ModelView modelView = myHandlerAdapter.handle(request, response, handler);

        String viewName = modelView.getViewName();
        MyView view = viewResolver(viewName);

        view.render(modelView.getModel(), request, response);

    }

    private MyHandlerAdapter getHandlerAdapter(Object handler) {
        for (MyHandlerAdapter adapter : handlerAdapterList) {
            if (adapter.supports(handler)) {
                return adapter;
            }
        }
        throw new IllegalArgumentException("Handler Adapter Not Found");
    }

    private Object getHandler(HttpServletRequest request) {
        String requestURI = request.getRequestURI();
        return handlerControllerMap.get(requestURI);
    }

    private MyView viewResolver(String viewName) {
        return new MyView(String.format("/WEB-INF/views/%s.jsp", viewName));
    }

}
