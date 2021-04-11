package com.exercise.testspringboot;

import java.io.Serializable;

public class ResponseData implements Serializable {

    //0表示成功，-1表示失败
    private int code;

    private Object data;

    private String msg;

    public ResponseData(int code, Object data) {
        super();
        this.code = code;
        this.data = data;
    }

    public ResponseData(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}
