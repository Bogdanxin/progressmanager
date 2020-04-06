package com.softlab.progressmanager.core.model;

import lombok.Data;

/**
 * @author gwx
 * @version 1.0
 * @program User
 * @description 用户实体类
 * @date 2020/3/11 14:34
 */
@Data
public class User {

    /**
     * 自增id，用于查找
     */
    private int userId;

    /**
     * 教师id，用于登录
     */
    private int teacherId;

    /**
     * 用户名，教师姓名
     */
    private String userName;

    /**
     * 账号密码
     */
    private String password;

    /**
     * 学院
     */
    private String userAcademy;

    /**
     * 用户token
     */
    private String token;

    /**
     * 用户类型 0=普通用户 1=管理用户
     */
    private int userType;
}
