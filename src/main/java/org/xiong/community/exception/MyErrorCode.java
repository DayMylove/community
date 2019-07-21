package org.xiong.community.exception;

public enum  MyErrorCode implements IMyErrorCode{

    QUESTION_NOT_FOUND("没有这个问题");

    private String message;

    MyErrorCode(String message) {
        this.message = message;
    }

    @Override
    public String getMessage() {
        return message;
    }
}
