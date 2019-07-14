package org.xiong.community.service;

import org.xiong.community.dto.QuestionDTO;
import org.xiong.community.entity.Question;
import org.xiong.community.entity.User;
import org.xiong.community.mapper.QuestionMapper;
import org.xiong.community.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class QuestionService {

    @Autowired
    private QuestionMapper questionMapper;

    @Autowired
    private UserMapper userMapper;

    //获得首页信息条
    public List<QuestionDTO> getIndexList(){
        List<Question> indexAllList = questionMapper.getIndexAllList();
        List<QuestionDTO> list=new ArrayList<>();
        for (Question question : indexAllList) {
            User users = userMapper.findUserbyId(question.getId());
            QuestionDTO qd = new QuestionDTO(question,users);
            list.add(qd);
        }
        return list;
    }
}
