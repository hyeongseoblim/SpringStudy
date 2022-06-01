package hello.springmvc.basic.requestmapping;

import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

@RestController
@Slf4j
public class RequestParamController {
    private Logger logger = LoggerFactory.getLogger(getClass());
    @RequestMapping("request-param-v2")
    @ResponseBody
    public String requestParamV2(
            @RequestParam("username") String username,
            @RequestParam("age") int age){
        logger.info("username "+username + " age "+age);
        return "ok";
    }
    @RequestMapping("request-param-required")
    @ResponseBody
    public String requestParamRequired(
            @RequestParam(value = "username",required = true) String username,
            @RequestParam(value = "age",required = false) int age){
        logger.info("username "+username + " age "+age);
        return "ok";
    }


    @RequestMapping("/model-attribute-v1")
    @ResponseBody
    public String modelparam(@ModelAttribute RequestParams requestParams){
        logger.info("username "+requestParams.getUsername() + " age "+requestParams.getAge());
        return "ok";
    }


}
