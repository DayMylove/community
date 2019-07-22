package org.xiong.community.ctroller;

import org.springframework.web.bind.annotation.*;
import org.xiong.community.entity.Question;
import org.xiong.community.entity.User;
import org.xiong.community.mapper.QuestionMapper;
import org.xiong.community.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.xiong.community.service.QuestionService;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

@Controller
public class publishController {

    @Autowired
    private QuestionService questionService;

    @RequestMapping(value = "/publish/{id}", method = RequestMethod.GET)
    public String edit(
            @PathVariable("id") Integer id,
            Model model) {
        Question question = questionService.findByid(id);
        model.addAttribute("title", question.getTitle());
        model.addAttribute("tag", question.getTags());
        model.addAttribute("description", question.getDescription());
        model.addAttribute("id", question.getId());
        return "publish";
    }

    @RequestMapping(value = "/publish", method = RequestMethod.POST)
    public String doPublish(
            @RequestParam("title") String title,
            @RequestParam("description") String description,
            @RequestParam("tag") String tags,
            @RequestParam(name = "id", defaultValue = "") String id,
            Model model,
            HttpServletRequest request) {
        //读取表单信息并判断
        model.addAttribute("title", title);
        model.addAttribute("tag", tags);
        model.addAttribute("description", description);
        if (title == null || title == "") {
            model.addAttribute("error", "标题不能为空");
            return "publish";
        }
        if (description == null || description == "") {
            model.addAttribute("error", "描述不能为空");
            return "publish";
        }
        if (tags == null || tags == "") {
            model.addAttribute("error", "标签不能为空");
            return "publish";
        }
        User user = (User) request.getSession().getAttribute("user");
        if (user == null) {
            model.addAttribute("error", "未登录");
            return "publish";
        }
        Question question = new Question();
        question.setTitle(title);
        question.setDescription(description);
        question.setTags(tags);
        question.setGmt_creat(System.currentTimeMillis());
        question.setCreator(user.getId());
        if (!id.equals("")) {
            question.setId(Integer.valueOf(id));
            question.setGmt_modifid(System.currentTimeMillis());
        }
        questionService.creatOrUpdate(question);
        return "publish";
//        return "index";
    }
}
