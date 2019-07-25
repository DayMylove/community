package org.xiong.community.dto;

import org.xiong.community.entity.Comment;
import org.xiong.community.entity.User;

public class CommentCreatDTO {
    private User user;
    private Comment comment;

    public CommentCreatDTO(User user, Comment comment) {
        this.user = user;
        this.comment = comment;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Comment getComment() {
        return comment;
    }

    public void setComment(Comment comment) {
        this.comment = comment;
    }
}
