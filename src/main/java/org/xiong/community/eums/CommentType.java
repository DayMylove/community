package org.xiong.community.eums;

/**
 * 判断评论类型
 * 是对问题评论还是对评论的评论
 */
public enum CommentType {

    //预设值
    QUESTION(1),
    COMMENT(2);

    private Integer type;

    CommentType(Integer type) {
        this.type = type;
    }

    public Integer getType() {
        return type;
    }

    public static boolean isExist(Integer type) {
        for (CommentType commentType : CommentType.values()) {
            if(commentType.getType()==type)
                return true;
        }
        return false;
    }
}
