package org.xiong.community.interceptor;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;
import org.xiong.community.exception.QuestionException;

import javax.servlet.http.HttpServletRequest;

/**
 * 错误信息拦截处理
 */
@ControllerAdvice
public class PageControllerAdvice {

    @ExceptionHandler(Exception.class)
    public ModelAndView exception(
            HttpServletRequest request,
            Throwable ex,
            Model model){
        if(ex instanceof QuestionException){
            model.addAttribute("errormessage",ex.getMessage());
        }else {
            model.addAttribute("errormessage","出错了");
        }
        return new ModelAndView("error");
    }
}
