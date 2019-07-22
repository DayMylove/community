package org.xiong.community.ctroller;

import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

/**
 * 其他错误页面统一处理
 */
@Controller
@RequestMapping("error")
public class MyErrorController implements ErrorController {

    @Override
    public String getErrorPath() {
        return "error";
    }

    @RequestMapping(produces = MediaType.TEXT_HTML_VALUE)
    public ModelAndView errorhtml(Model model){
        model.addAttribute("页面未找到");
        return new ModelAndView("error");
    }
}
