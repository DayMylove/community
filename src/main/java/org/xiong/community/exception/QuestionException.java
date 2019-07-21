package org.xiong.community.exception;

public class QuestionException extends RuntimeException{
    private String message;

    public QuestionException(IMyErrorCode iMyErrorCode) {
        this.message = iMyErrorCode.getMessage();
    }
    public QuestionException(String message) {
        this.message = message;
    }
    @Override
    public String getMessage() {
        return message;
    }
}
