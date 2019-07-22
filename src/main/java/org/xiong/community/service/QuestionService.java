package org.xiong.community.service;

import org.xiong.community.dto.PageDTO;
import org.xiong.community.dto.QuestionDTO;
import org.xiong.community.entity.Question;
import org.xiong.community.entity.User;
import org.xiong.community.exception.MyErrorCode;
import org.xiong.community.exception.MyException;
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
    public PageDTO getIndexList(Integer page, int size){
        //将页码转换为当前页第一条数据在数据库中的排名
        page=size*(page-1);
        List<Question> indexAllList = questionMapper.getIndexAllList(page,size);
        List<QuestionDTO> list=new ArrayList<>();
        for (Question question : indexAllList) {
            User users = userMapper.findUserbyId(question.getCreator());
            QuestionDTO qd = new QuestionDTO(question,users);
            list.add(qd);
        }
        PageDTO pageDTO=new PageDTO();
        pageDTO.setQuestionDTOList(list);
        pageDTO.pageInit(questionMapper.count(),page,size);
        return pageDTO;
    }

    public PageDTO list(Integer usrId, Integer page, Integer size) {
        page=size*(page-1);
        List<Question> indexAllList = questionMapper.list(usrId,page,size);
        List<QuestionDTO> list=new ArrayList<>();
        for (Question question : indexAllList) {
            User users = userMapper.findUserbyId(question.getCreator());
            QuestionDTO qd = new QuestionDTO(question,users);
            list.add(qd);
        }
        PageDTO pageDTO=new PageDTO();
        pageDTO.setQuestionDTOList(list);
        pageDTO.pageInit(questionMapper.count(),page,size);
        return pageDTO;
    }

    //获得详情页面
    public QuestionDTO getByQuestionId(Integer questionId) {
        QuestionDTO questionDTO=new QuestionDTO();
        Question question = questionMapper.getByid(questionId);
        if(question==null){
            throw new MyException(MyErrorCode.QUESTION_NOT_FOUND);
        }
        questionDTO.setQuestion(question);
        User user = questionMapper.getUserByQuestion(questionId);
        questionDTO.setUser(user);
        return questionDTO;
    }

    //创建或者更新数据
    public void creatOrUpdate(Question question) {
        if(questionMapper.isExist(question.getId())>0){
            int code=questionMapper.update(question);
            if(code!=1){
                throw new MyException(MyErrorCode.QUESTION_NOT_FOUND);
            }
        }else {
            question.setGmt_modifid(System.currentTimeMillis());
            questionMapper.creat(question);
        }
    }

    //获得问题
    public Question findByid(Integer id){
        return questionMapper.getByid(id);
    }

    //增加阅读数
    public void increaseView(Integer questionId) {
        questionMapper.addView(questionId);
    }
}
