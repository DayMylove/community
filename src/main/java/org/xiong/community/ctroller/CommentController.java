package org.xiong.community.ctroller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.xiong.community.dto.CommentDTO;
import org.xiong.community.dto.ResultDTO;
import org.xiong.community.entity.Comment;
import org.xiong.community.entity.User;
import org.xiong.community.service.CommentService;

import javax.servlet.http.HttpServletRequest;

@Controller
public class CommentController {

    @Autowired
    private CommentService commentService;

    @ResponseBody
    @RequestMapping(value = "/comment", method = RequestMethod.POST)
    public Object commentpost(
            @RequestBody CommentDTO commentDTO,
            HttpServletRequest request) {
        User user = (User) request.getSession().getAttribute("user");
        if(user==null){
            return ResultDTO.error(200,"未登录不可以评论");
        }
        Comment comment = new Comment();
        comment.setParentId(commentDTO.getParentId());
        comment.setType(commentDTO.getType());
        comment.setContent(commentDTO.getContent());
        comment.setGmtCreat(System.currentTimeMillis());
        comment.setGmtModifid(comment.getGmtCreat());
        comment.setCommentor(13);
        commentService.insert(comment);
        return null;
    }
}
