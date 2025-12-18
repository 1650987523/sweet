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

}
