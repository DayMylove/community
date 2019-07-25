package org.xiong.community.ctroller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.xiong.community.dto.CommentCreatDTO;
import org.xiong.community.dto.CommentDTO;
import org.xiong.community.dto.QuestionDTO;
import org.xiong.community.service.CommentService;
import org.xiong.community.service.QuestionService;

import java.util.List;

@Controller
public class QuestionController {

    @Autowired
    private QuestionService questionService;
    @Autowired
    private CommentService commentService;

    @RequestMapping(value = "/question/{id}",method = RequestMethod.GET)
    public String detail(
            @PathVariable("id")Integer questionId,
            Model model){
        QuestionDTO questionDTO=questionService.getByQuestionId(questionId);
        questionService.increaseView(questionId);
        model.addAttribute("question",questionDTO);
        List<CommentCreatDTO> commentCreatDTOS = commentService.getQuestinComment(questionId);
        model.addAttribute("comments", commentCreatDTOS);
        return "question";
    }
}
