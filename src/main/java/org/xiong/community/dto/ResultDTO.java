package org.xiong.community.dto;

import org.xiong.community.exception.MyErrorCode;
import org.xiong.community.exception.MyException;

/**
 * 回馈原始数据信息
 */
public class ResultDTO {
    private Integer Code;
    private String message;

    public static ResultDTO error(Integer code,String message){
        ResultDTO resultDTO = new ResultDTO();
        resultDTO.setCode(code);
        resultDTO.setMessage(message);
        return resultDTO;
    }

    public static ResultDTO error(MyErrorCode noLogin) {
        return error(noLogin.getCode(),noLogin.getMessage());
    }
    public static ResultDTO ok() {
        ResultDTO resultDTO = new ResultDTO();
        resultDTO.setCode(10);
        resultDTO.setMessage("请求成功");
        return resultDTO;
    }

    public static ResultDTO error(MyException ex) {
        return error(ex.getCode(),ex.getMessage());
    }

    public Integer getCode() {
        return Code;
    }

    public void setCode(Integer code) {
        Code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
