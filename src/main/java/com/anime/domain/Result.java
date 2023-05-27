package com.anime.domain;

import java.io.Serializable;

public class Result<T> implements Serializable {
    private static final long serialVersionUID = 1L;

    private String type;
    private String message;
    private T data;

    public Result() {
    }

    public Result(String type) {
        this.type = type;
    }

    public Result(String type, String message) {
        this.type = type;
        this.message = message;
    }

    public Result(String type, String message, T data) {
        this.type = type;
        this.message = message;
        this.data = data;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}
