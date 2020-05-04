package com.wez.common.entity;

public class Result<T> {

    public static final String OK = "OK";
    public static final String ERROR = "ERROR";

    private String ret;
    private String msg;
    private T dataStore;

    public Result() {
    }

    public Result(T dataStore) {
        this(OK, null, dataStore);
    }
    
    public Result(String ret) {
        this(ret, null, null);
    }
    
    public Result(String ret, String msg) {
        this(ret, msg, null);
    }
    
    public Result(String ret, T dateStore) {
        this(ret, null, dateStore);
    }

    public Result(String ret, String msg, T dateStore) {
        this.ret = ret;
        this.msg = msg;
        this.dataStore = dateStore;
    }

    public static <T> Result<T> ok() {
        return new Result<T>(OK, null, null);
    }
    
    public static <T> Result<T> ok(T dataStore) {
        return new Result<T>(dataStore);
    }

    public static <T> Result<T> error(String message) {
        return new Result<T>(ERROR, message);
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
