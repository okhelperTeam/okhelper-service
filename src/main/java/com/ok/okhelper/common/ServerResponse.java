package com.ok.okhelper.common;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import org.springframework.http.HttpStatus;

import java.io.Serializable;

/**
 * Created by zc on 2018/4/9.
 *
 * @param <T>
 */

//去掉空值得空key
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ServerResponse<T> implements Serializable {
    private int status;
    private String msg;
    private T data;

    private ServerResponse(int status) {
        this.status = status;
    }

    private ServerResponse(int status, T data) {
        this.status = status;
        this.data = data;
    }

    private ServerResponse(int status, String msg, T data) {
        this.status = status;
        this.msg = msg;
        this.data = data;
    }

    private ServerResponse(int status, String msg) {
        this.status = status;
        this.msg = msg;
    }

    //忽略isSuccess不把它序列化
    @JsonIgnore
    public boolean isSuccess() {
        return this.status == HttpStatus.OK.value();
    }

    public int getStatus() {
        return status;
    }

    public T getData() {
        return data;
    }

    public String getMsg() {
        return msg;
    }


    public static <T> ServerResponse<T> createBySuccess() {
        return new ServerResponse<>(HttpStatus.OK.value());
    }

    public static <T> ServerResponse<T> createBySuccess(T data) {
        return new ServerResponse<>(HttpStatus.OK.value(), data);
    }

    public static <T> ServerResponse<T> createBySuccess(String msg, T data) {
        return new ServerResponse<>(HttpStatus.OK.value(), msg, data);
    }

    public static <T> ServerResponse<T> createBySuccessCodeMessages(int code, String msg) {
        return new ServerResponse<>(code, msg);
    }

    public static <T> ServerResponse<T> createBySuccessMessage(String msg) {
        return new ServerResponse<>(HttpStatus.OK.value(), msg);
    }

    //500
    public static <T> ServerResponse<T> createDefaultErrorMessage(String errorMessage) {
        return new ServerResponse<>(HttpStatus.INTERNAL_SERVER_ERROR.value(), errorMessage);
    }

    public static <T> ServerResponse<T> createByErrorCodeMessage(int errorCode, String errorMessage) {
        return new ServerResponse<>(errorCode, errorMessage);
    }
}
