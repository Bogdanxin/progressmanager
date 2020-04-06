package com.softlab.progressmanager.common;

/**
 * @author gwx
 * @version 1.0
 * @program  ProException
 * @description 继承Exception类，处理异常
 * @date 2020/3/11 11:50
 */
public class ProException extends Exception {
    public ProException(String message){
        super(message);
    }
}
