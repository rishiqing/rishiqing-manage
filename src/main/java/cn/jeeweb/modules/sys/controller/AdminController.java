package cn.jeeweb.modules.sys.controller;

import cn.jeeweb.core.common.controller.BaseController;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/admin")
public class AdminController extends BaseController {

    @RequestMapping(value = "/")
    public ModelAndView login() {
        return new ModelAndView("redirect:"+"/rishiqing");
    }
}
