package hello.springmvc.basic.responseMapping;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class ResponseViewController {

    @RequestMapping("/response-view-v1")
    public ModelAndView responseViewV1() {
        ModelAndView modelAndView = new ModelAndView("response/hello").addObject("data", "hello!");
        return modelAndView;
    }
}
