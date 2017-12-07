package com.jaso.base.util;

/**
 * Created by dllo on 17/12/6.
 */
public class AjaxResult <T> {

    private boolean status = true;// 是否成功标志:默认为true
    private T resultData;// 成功时返回的数据
    private String message;// 错误信息

    public AjaxResult() {
    }

    /**
     *  成功时的构造器
     */
    public AjaxResult(boolean status, T resultData) {
        this.status = status;
        this.resultData = resultData;
    }

    /**
     * 错误时的构造器
     */
    public AjaxResult(boolean success, String message) {
        this.status = success;
        this.message = message;
    }

    public AjaxResult(boolean status, T resultData, String message) {
        this.status = status;
        this.resultData = resultData;
        this.message = message;
    }

    @Override
    public String toString() {
        return "AjaxResult{" +
                "status=" + status +
                ", resultData=" + resultData +
                ", message='" + message + '\'' +
                '}';
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public T getResultData() {
        return resultData;
    }

    public void setResultData(T data) {
        this.resultData = data;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
