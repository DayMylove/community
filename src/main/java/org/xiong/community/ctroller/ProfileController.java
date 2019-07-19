package org.xiong.community.ctroller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.xiong.community.dto.PageDTO;
import org.xiong.community.entity.User;
import org.xiong.community.service.QuestionService;

import javax.servlet.http.HttpServletRequest;

@Controller
public class ProfileController {


    @Autowired
    private QuestionService questionService;

    /**
     * 访问个人中心，判断个人中心模块
     *
     * @param action
     * @param model
     * @return
     */
    @RequestMapping(value = "/profile/{action}", method = RequestMethod.GET)
    public String profile(
            @PathVariable(name = "action") String action,
            Model model,
            HttpServletRequest request,
            @RequestParam(name = "page", defaultValue = "1")Integer page,
            @RequestParam(name = "size", defaultValue = "5")Integer size) {

        User user= (User) request.getSession().getAttribute("user");
        if (user == null)
            return "redirect:/";
        if ("questions".equals(action)) {
            model.addAttribute("section", "question");
            model.addAttribute("sectionName", "我的提问");
            PageDTO list = questionService.list(user.getId(), page, size);
            model.addAttribute("questionPageInfo",list);
        }
        if ("repies".equals(action)) {
            model.addAttribute("section", "repies");
        }
        return "profile";
    }

}
