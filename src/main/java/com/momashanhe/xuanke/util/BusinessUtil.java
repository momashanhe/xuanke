package com.momashanhe.xuanke.util;

import java.io.Serializable;

public class BusinessUtil implements Serializable {
    private static final long serialVersionUID = 2469099307955640173L;

    public static <T> Result<T> success() {
        return new Result<>(200, "操作成功");
    }

    public static <T> Result<T> success(T data) {
        return new Result<>(200, "操作成功", data);
    }

    public static <T> Result<T> error(String message) {
        return new Result<>(500, message);
    }

    public static class Result<T> implements Serializable {
        private static final long serialVersionUID = 3964688156413021829L;
        private Integer code;
        private String message;
        private T data;

        public Result() {
        }

        public Result(Integer code, String message) {
            this.code = code;
            this.message = message;
        }

        public Result(Integer code, String message, T data) {
            this.code = code;
            this.message = message;
            this.data = data;
        }

        public Integer getCode() {
            return code;
        }

        public void setCode(Integer code) {
            this.code = code;
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
}