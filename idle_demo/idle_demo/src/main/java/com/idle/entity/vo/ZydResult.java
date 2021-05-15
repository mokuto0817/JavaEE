package com.idle.entity.vo;

import com.fasterxml.jackson.annotation.JsonInclude;

import java.io.Serializable;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class ZydResult<T> implements Serializable {
    private static final long serialVersionUID = 1L;

    private boolean status;
    private String msg;
    private T data;
    private Integer code;

    public ZydResult() {
        super();
    }

    public ZydResult(boolean status, String msg) {
        super();
        this.status = status;
        this.msg = msg;
    }

    public ZydResult(boolean status, String msg, T data) {
        super();
        this.status = status;
        this.msg = msg;
        this.data = data;
    }

    public ZydResult(boolean status, String msg, T data, Integer code) {
        super();
        this.status = status;
        this.msg = msg;
        this.data = data;
        this.code = code;
    }

    public ZydResult(boolean status, String msg, Integer code) {
        super();
        this.status = status;
        this.msg = msg;
        this.code = code;
    }



    public static <T> ZydResult<T> SUCCESS() {
        return new ZydResult<T>(true, "success");
    }

    public static <T> ZydResult<T> SUCCESS(T data) {
        return new ZydResult<T>(true, "success", data);
    }

    public static <T> ZydResult<T> SUCCESSMSG(String msg) {
        return new ZydResult<T>(true, msg);
    }

    public static <T> ZydResult<T> SUCCESS(String msg, T data) {
        return new ZydResult<T>(true, msg, data);
    }

    public static <T> ZydResult<T> SUCCESS(String msg, T data, Integer code) {
        return new ZydResult<T>(true, msg, data, code);
    }

    public static <T> ZydResult<T> FAIL(T data, String msg) {
        return new ZydResult<T>(false, msg, data);
    }

    public static <T> ZydResult<T> FAIL(T data, String msg, Integer code) {
        return new ZydResult<T>(false, msg, data, code);
    }

    public static <T> ZydResult<T> FAIL(String msg) {
        return new ZydResult<T>(false, msg);
    }

    public static <T> ZydResult<T> FAIL(String msg, Integer code) {
        return new ZydResult<T>(false, msg,code);
    }

    public static <T> ZydResult<T> FAIL() {
        return new ZydResult<T>(false, "fail");
    }

    public static <T> ZydResult<T> SUCCESSORFAIL(boolean status, String trueMsg, String falseMsg) {
        if(status){
            return new ZydResult<T>(true, trueMsg);
        }else{
            return new ZydResult<T>(false, falseMsg);
        }
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }
}
