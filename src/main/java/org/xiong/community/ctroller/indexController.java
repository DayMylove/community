package org.xiong.community.ctroller;

import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.xiong.community.dto.PageDTO;
import org.xiong.community.dto.QuestionDTO;
import org.xiong.community.entity.User;
import org.xiong.community.mapper.UserMapper;
import org.xiong.community.service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
public class indexController {

    @Autowired
    private QuestionService questionService;

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String index(HttpServletRequest request,
                        Model model,
                        @RequestParam(name = "page", defaultValue = "1") Integer page) {
        PageDTO questionDTOList = questionService.getIndexList(page,5);
        model.addAttribute("questionPageInfo", questionDTOList);
        return "index";
    }
}
