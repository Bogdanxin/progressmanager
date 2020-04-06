package com.softlab.progressmanager.service;

import com.softlab.progressmanager.common.ProException;
import com.softlab.progressmanager.common.RestData;
import com.softlab.progressmanager.core.model.User;

import java.util.List;
import java.util.Map;

/**
 * @author gwx
 * @version 1.0
 * @className UserService
 * @description 用户service层
 * @date 2020/3/11 19:47
 */
public interface UserService {

    /**
     * 删除指定id的用户
     * @param userId
     * @return
     * @throws ProException
     */
    RestData deleteUserById(int userId) throws ProException;

    /**
     * 添加一个课程
     * @param user
     * @return
     * @throws ProException
     */
    RestData insertUser(User user) throws ProException;

    /**
     * 修改指定id的用户
     * @param userId
     * @param user
     * @return
     * @throws ProException
     */
    RestData updateUserById(int userId, User user) throws ProException;

    /**
     * 查看指定id的用户
     * @param userId
     * @return
     * @throws ProException
     */
    Map<String, Object> selectUserById(int userId) throws ProException;

    /**
     * 查看不同条件下的用户
     * @param user
     * @return
     * @throws ProException
     */
    List<Map<String, Object>> selectUsersByCondition(User user) throws ProException;

    /**
     * 将用户token置空，用于退出登录
     * @param userId
     * @return
     * @throws ProException
     */
    RestData updateTokenNullById(int userId) throws ProException;

    /**
     * 查看指定密码和id的用户，用于登录
     * @param password
     * @param teacherId
     * @return
     * @throws ProException
     */
    Map<String, Object> selectUserByPasswordAndId(String password, Integer teacherId) throws ProException;

}
