package org.xiong.community.ctroller;

import org.xiong.community.entity.Question;
import org.xiong.community.entity.User;
import org.xiong.community.mapper.QuestionMapper;
import org.xiong.community.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

@Controller
public class publishController {

    @Autowired
    private QuestionMapper questionMapper;

    @Autowired
    private UserMapper userMapper;

    @GetMapping("/publish")
    public String publish(){

        return "publish";
    }

    @PostMapping("/publish")
    public String doPublish(
            @RequestParam("title")String title,
            @RequestParam("description")String description,
            @RequestParam("tag")String tags,
            Model model,
            HttpServletRequest request
    ){
        //读取表单信息并判断
        model.addAttribute("title",title);
        model.addAttribute("tag",tags);
        model.addAttribute("description",description);
        if (title==null||title==""){
            model.addAttribute("error","标题不能为空");
            return "publish";
        }
        if(description==null||description==""){
            model.addAttribute("error","描述不能为空");
            return "publish";
        }
        if(tags==null||tags==""){
            model.addAttribute("error","标签不能为空");
            return "publish";
        }
        //判断是否登录
        User user=null;
        Cookie[] cookies = request.getCookies();
        boolean flag=false;
        if (cookies!=null)
        for (Cookie c:cookies){
            if(c.getName().equals("token")){
                String token=c.getValue();
                user = userMapper.findByToken(token);
//                System.out.println(user.toString());
                if(user!=null){
                    request.getSession().setAttribute("user",user);
                    flag=true;
                }
                break;
            }
        }
        if (!flag){
            model.addAttribute("error","未登录");
            return "publish";
        }
        Question question = new Question();
        question.setTitle(title);
        question.setDescription(description);
        question.setTags(tags);
        question.setId(user.getId());
        question.setGmt_creat(System.currentTimeMillis());
        questionMapper.creat(question);
        return "publish";
    }
}
