package org.xiong.community.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.xiong.community.entity.Comment;
import org.xiong.community.mapper.CommentMapper;

@Service
public class CommentService {

    @Autowired
    private CommentMapper commentMapper;


    public void insert(Comment comment) {
        commentMapper.insert(comment);
    }
}
