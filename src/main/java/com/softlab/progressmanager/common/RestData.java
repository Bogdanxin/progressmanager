package com.softlab.progressmanager.common;

import com.fasterxml.jackson.annotation.JsonInclude;

/**
 * @author gwx
 * @version 1.0
 * @program  RestData
 * @description 用来处理前后端数据类
 * @date 2020/3/11 11:41
 */
public class RestData {

    /**
     * 0=操作成功  1=操作失败
     */
    private int code;

     /**
     * 传递操作信息
     */
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String message;

    /**
     * 传递实体
     */
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private Object data;

    public RestData(int code, String message){
        this.code = code;
        this.message = message;
    }

    public RestData(Object data){
        this.code = 0;
        this.message = "success!";
        this.data = data;
    }

    /**
     * 千万不要忘记加上get和set方法
     */
    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }
}
