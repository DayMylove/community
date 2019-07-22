package org.xiong.community.interceptor;

import com.alibaba.fastjson.support.spring.FastJsonJsonView;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;
import org.xiong.community.dto.ResultDTO;
import org.xiong.community.exception.MyErrorCode;
import org.xiong.community.exception.MyException;

import javax.servlet.http.HttpServletRequest;

/**
 * 错误信息统一拦截处理
 */
@ControllerAdvice
public class PageControllerAdvice {

    //这个方法返回view或者json
    @ExceptionHandler(Exception.class)
    public ModelAndView exception(
            HttpServletRequest request,
            Throwable ex,
            Model model){
        //判断传过来的错误信息是什么请求类型
        String contentType = request.getContentType();
        if(contentType.equals("application/json")){ //判断是否是post传递json信息
            //返回JSON
            if (ex instanceof MyException) {
                ModelAndView mav=new ModelAndView(new FastJsonJsonView());
                ResultDTO error = ResultDTO.error((MyException) ex);
                mav.addObject("messqge",error.getMessage());
                mav.addObject("code",error.getCode());
                return mav;
            } else {
                ModelAndView mav=new ModelAndView(new FastJsonJsonView());
                ResultDTO error = ResultDTO.error(MyErrorCode.SYSTEM_ERROR);
                mav.addObject("code",error.getCode());
                mav.addObject("message",error.getMessage());
                return mav;
            }
        }else { //判断是否是get请求网页
            //返回错误页面
            if (ex instanceof MyException) {
                model.addAttribute("errormessage", ex.getMessage());
            } else {
                model.addAttribute("errormessage", MyErrorCode.SYSTEM_ERROR.getMessage());
            }
            return new ModelAndView("error");
        }
    }
}
