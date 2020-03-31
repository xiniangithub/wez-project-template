package com.wez.common.entity;

public class Result<T> {

    public static final String OK = "OK";
    public static final String ERROR = "ERROR";

    private String ret;
    private String msg;
    private T dataStore;
    private Throwable throwable;

    public Result() {
    }

    public Result(T dataStore) {
        this(OK, null, dataStore);
    }

    public Result(String ret, String message) {
        this(ret, message, null);
    }

    public Result(String ret, String msg, T dateStore) {
        this.ret = ret;
        this.msg = msg;
        this.dataStore = dateStore;
    }

    public static <T> Result ok(String message, T dataStore) {
        return new Result(OK, message, dataStore);
    }

    public static Result error(String message) {
        return new Result(ERROR, message);
    }

    public String getRet() {
        return ret;
    }

    public void setRet(String ret) {
        this.ret = ret;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public T getDataStore() {
        return dataStore;
    }

    public void setDataStore(T dataStore) {
        this.dataStore = dataStore;
    }
}
