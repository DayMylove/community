package org.xiong.community.dto;

import org.xiong.community.entity.Question;
import org.xiong.community.entity.User;
import org.springframework.beans.BeanUtils;

public class QuestionDTO {

    private Question question=new Question();
    private User user=new User();

    public QuestionDTO(Question question, User user) {
        BeanUtils.copyProperties(question, this.question);
        BeanUtils.copyProperties(user, this.user);
    }

    public Question getQuestion() {
        return question;
    }

    public void setQuestion(Question question) {
        this.question = question;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
