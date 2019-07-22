package org.xiong.community.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.xiong.community.entity.Comment;
import org.xiong.community.entity.Question;
import org.xiong.community.eums.CommentType;
import org.xiong.community.exception.MyErrorCode;
import org.xiong.community.exception.MyException;
import org.xiong.community.mapper.CommentMapper;
import org.xiong.community.mapper.QuestionMapper;

@Service
public class CommentService {

    @Autowired
    private CommentMapper commentMapper;
    @Autowired
    private QuestionMapper questionMapper;


    public void insert(Comment comment) {
        if(comment.getParentId()==null||comment.getParentId()==0){
            //评论时，没有选中目标(问题或回复)
            throw new MyException(MyErrorCode.TARGET_PARAM_NOT_FOUND);
        }
        if(comment.getType()==null|| !CommentType.isExist(comment.getType())){
            //评论类型参数错误
            throw new MyException(MyErrorCode.TYPE_PARAM_NOT_EXIST);
        }
        if(comment.getType()==CommentType.COMMENT.getType()){
            //回复评论
            Comment Pcomment=commentMapper.selectByParentId(comment.getParentId());
            if(Pcomment==null){
                //回复的评论不存在
                throw new MyException(MyErrorCode.COMMENT_NOT_FOUND);
            }else {
                commentMapper.insert(comment);
            }
        }
        if(comment.getType()==CommentType.QUESTION.getType()){
            //回复问题
            Question question=questionMapper.getByid(comment.getParentId());
            if(question==null){
                //回复的问题不存在
                throw new MyException(MyErrorCode.QUESTION_NOT_FOUND);
            }else {
                commentMapper.insert(comment);
                questionMapper.increaseCommentCount(question);
            }
        }
    }
}
