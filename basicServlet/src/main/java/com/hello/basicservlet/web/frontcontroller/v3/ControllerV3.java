package com.hello.basicservlet.web.frontcontroller.v3;

import com.hello.basicservlet.web.frontcontroller.ModelView;

import java.util.Map;

public interface ControllerV3 {
    ModelView process (Map<String, String> paramMap);
}
