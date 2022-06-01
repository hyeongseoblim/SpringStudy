package com.rich.web;


import com.rich.common.Logger;
import lombok.RequiredArgsConstructor;
import org.apache.tomcat.jni.Time;
import org.springframework.beans.factory.ObjectProvider;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

@Controller
@RequiredArgsConstructor
public class LogController {

    private final LogService logService;
    private final Logger logger;

    @RequestMapping("log-demo")
    @ResponseBody
    public String logDemo(HttpServletRequest request) throws InterruptedException {
        String requestUrl = request.getRequestURL().toString();
        logger.setRequestUrl(requestUrl);
        logger.log("Controller test");
        Thread.sleep(1000);
        logService.logic("testId");
        return "OK";
    }
}
