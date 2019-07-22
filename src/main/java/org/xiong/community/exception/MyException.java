package org.xiong.community.exception;

/**
 * 创建自己的异常
 * 接受问题代码和描述
 */

public class MyException extends RuntimeException{
    private String message;
    private Integer code;

    public MyException(IMyErrorCode iMyErrorCode) {
        this.message = iMyErrorCode.getMessage();
        this.code=iMyErrorCode.getCode();
    }
    @Override
    public String getMessage() {
        return message;
    }

    public Integer getCode() {
        return code;
    }
}
