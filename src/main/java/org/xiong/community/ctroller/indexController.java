package org.xiong.community.ctroller;

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
    private UserMapper userMapper;

    @Autowired
    private QuestionService questionService;

    @RequestMapping("/")
    public String index(HttpServletRequest request,
                        Model model) {
        //登录持久化
        Cookie[] cookies = request.getCookies();
        if (cookies != null) {
            for (Cookie c : cookies) {
                if (c.getName().equals("token")) {
                    String token = c.getValue();
                    User user = userMapper.findByToken(token);
                    if (user != null) {
                        request.getSession().setAttribute("user", user);
                    }
                    break;
                }
            }
        }
       List<QuestionDTO> questionDTOList=questionService.getIndexList();
        model.addAttribute("questionList",questionDTOList);
        return "index";
    }
}
