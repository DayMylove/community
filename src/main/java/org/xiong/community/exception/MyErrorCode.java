package org.xiong.community.exception;

/**
 * 集中管理错误代码和描述
 */
public enum  MyErrorCode implements IMyErrorCode{

    QUESTION_NOT_FOUND(2000,"没有这个问题"),
    TARGET_PARAM_NOT_FOUND(2001,"没有选中评论或者问题回复"),
    NO_LOGIN(2002,"未登录不可以评论"),
    SYSTEM_ERROR(2003,"出错了!!"),
    TYPE_PARAM_NOT_EXIST(2004,"评论类型错误"),
    COMMENT_NOT_FOUND(2005,"回复的评论不存在"),
    ;

    private String message;
    private Integer code;

    MyErrorCode(Integer code ,String message) {
        this.message = message;
        this.code=code;
    }

    @Override
    public String getMessage() {
        return message;
    }

    @Override
    public Integer getCode() {
        return code;
    }

}
