package hello.springmvc.basic.responseMapping;

import hello.springmvc.basic.requestmapping.RequestParams;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;

@RestController
@Slf4j
public class ResponseBodyController {

    @GetMapping("/response-body-string-v1")
    public void responseBodyV1(HttpServletResponse response) throws  Exception{
        response.getWriter().write("ok");
    }

    @GetMapping("/response-body-string-v2")
    public ResponseEntity<String> responseBodyV2(HttpServletResponse response) throws  Exception{
            return new ResponseEntity<>("ok", HttpStatus.OK);
    }
    
    @GetMapping("/response-body-string-v3")
    public String responseBodyV3(HttpServletResponse response) throws  Exception{
        return "ok";
    }


    @GetMapping("/response-body-json-v1")
    public ResponseEntity<RequestParams> responseBodyJsonV1(){
        RequestParams requestParams = new RequestParams();
        requestParams.setAge(20);
        requestParams.setUsername("hi");
        return new ResponseEntity<>(requestParams,HttpStatus.OK);
    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/response-body-json-v2")
    public RequestParams responseBodyJsonV2(){
        RequestParams requestParams = new RequestParams();
        requestParams.setAge(20);
        requestParams.setUsername("hi");
        return requestParams;
    }

}
