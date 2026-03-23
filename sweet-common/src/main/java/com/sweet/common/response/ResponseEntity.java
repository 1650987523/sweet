package com.sweet.common.response;

import lombok.Data;

import java.io.Serializable;
import java.util.Objects;

@Data
public class ResponseEntity<T> implements Serializable {
    private static final long serialVersionUID = 1L;

    private int code;

    private String msg;

    private T data;

    public boolean isSuccess(){
        return Objects.equals(ResponseCode.SUCCESS, this.code);
    }

    public static <T> ResponseEntity<T> success(T data) {
        ResponseEntity<T> responseEntity = new ResponseEntity<>();
        responseEntity.setData(data);
        responseEntity.setCode(ResponseCode.SUCCESS);
        return responseEntity;
    }

    public static <T> ResponseEntity<T> success() {
        ResponseEntity<T> responseEntity = new ResponseEntity<>();
        responseEntity.setCode(ResponseCode.SUCCESS);
        return responseEntity;
    }

    public static <T> ResponseEntity<T> success(String msg, T data) {
        ResponseEntity<T> responseEntity = new ResponseEntity<>();
        responseEntity.setData(data);
        responseEntity.setCode(ResponseCode.SUCCESS);
        responseEntity.setMsg(msg);
        return responseEntity;
    }

    public static <T> ResponseEntity<T> fail(T data) {
        ResponseEntity<T> responseEntity = new ResponseEntity<>();
        responseEntity.setData(data);
        responseEntity.setCode(ResponseCode.FAIL);
        return responseEntity;
    }

    public static <T> ResponseEntity<T> fail(int code, String msg, T data) {
        ResponseEntity<T> responseEntity = new ResponseEntity<>();
        responseEntity.setData(data);
        responseEntity.setCode(code);
        responseEntity.setMsg(msg);
        return responseEntity;
    }

    public static <T> ResponseEntity<T> fail(int code, T data) {
        ResponseEntity<T> responseEntity = new ResponseEntity<>();
        responseEntity.setData(data);
        responseEntity.setCode(code);
        return responseEntity;
    }

    public static <T> ResponseEntity<T> fail(int code, String msg) {
        ResponseEntity<T> responseEntity = new ResponseEntity<>();
        responseEntity.setCode(code);
        responseEntity.setMsg(msg);
        return responseEntity;
    }

    public static <T> ResponseEntity<T> fail(int code) {
        ResponseEntity<T> responseEntity = new ResponseEntity<>();
        responseEntity.setCode(code);
        return responseEntity;
    }

    public static <T> ResponseEntity<T> fail(String msg) {
        ResponseEntity<T> responseEntity = new ResponseEntity<>();
        responseEntity.setCode(ResponseCode.FAIL);
        responseEntity.setMsg(msg);
        return responseEntity;
    }

    public static <T> ResponseEntity<T> fail(String msg, T data) {
        ResponseEntity<T> responseEntity = new ResponseEntity<>();
        responseEntity.setData(data);
        responseEntity.setCode(ResponseCode.FAIL);
        responseEntity.setMsg(msg);
        return responseEntity;
    }

}
