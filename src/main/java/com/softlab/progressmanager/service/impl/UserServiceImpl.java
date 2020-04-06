package com.softlab.progressmanager.service.impl;

import com.softlab.progressmanager.common.ProException;
import com.softlab.progressmanager.common.RestData;
import com.softlab.progressmanager.common.utils.TokenUtils;
import com.softlab.progressmanager.core.mapper.UserMapper;
import com.softlab.progressmanager.core.model.User;
import com.softlab.progressmanager.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author gwx
 * @version 1.0
 * @className UserServiceImpl
 * @description 用户service层实现接口
 * @date 2020/3/12 8:56
 */
@Service
public class UserServiceImpl implements UserService {

    private final UserMapper userMapper;

    @Autowired
    public UserServiceImpl(UserMapper userMapper) {
        this.userMapper = userMapper;
    }

    @Override
    public RestData deleteUserById(int userId) throws ProException {
        if (userMapper.deleteUserById(userId) > 0) {
            return new RestData(0,"删除成功！");
        }else {
            throw new ProException("删除失败！");
        }
    }

    @Override
    public RestData insertUser(User user) throws ProException {
        if (userMapper.selectUsersByTeacherId(user.getTeacherId()).size() != 0) {
            return new RestData(1,"输入为重复的教师id！");
        }

        if (userMapper.insertUser(user) > 0) {
            return new RestData(0,"添加成功！");
        }else {
            throw new ProException("添加失败！");
        }
    }

    @Override
    public RestData updateUserById(int userId, User user) throws ProException {
        if (userMapper.updateUserById(userId, user) > 0) {
            return new RestData(0,"修改成功！");
        }else {
            throw new ProException("修改失败！");
        }
    }

    @Override
    public Map<String, Object> selectUserById(int userId) throws ProException {
        Map<String, Object> map = new HashMap<>(6);
        User user = userMapper.selectUserById(userId);
        if (user != null) {
            map.put("userId", user.getUserId());
            map.put("teacherId", user.getTeacherId());
            map.put("userName", user.getUserName());
            map.put("userAcademy", user.getUserAcademy());
            map.put("token", user.getToken());
            map.put("userType", user.getUserType());
        }else {
            throw new ProException("查找失败！");
        }
        return map;
    }

    @Override
    public List<Map<String, Object>> selectUsersByCondition(User user1) throws ProException {
        List<Map<String, Object>> al = new ArrayList<>();
        List<User> users = userMapper.selectUsersByCondition(user1);
        if (users != null && users.size() > 0) {
            for (User user : users){
                Map<String, Object> map = new HashMap<>(6);
                map.put("userId", user.getUserId());
                map.put("teacherId", user.getTeacherId());
                map.put("userName", user.getUserName());
                map.put("userAcademy", user.getUserAcademy());
                map.put("token", user.getToken());
                map.put("userType", user.getUserType());
                al.add(map);
            }
        }else {
            throw new ProException("查找失败！");
        }
        return al;
    }

    @Override
    public RestData updateTokenNullById(int userId) throws ProException {
        if (userMapper.updateTokenNullById(userId) > 0) {
            return new RestData(0,"退出成功！");
        }else {
            throw new ProException("退出失败！");
        }
    }

    @Override
    public Map<String, Object> selectUserByPasswordAndId(String password, Integer teacherId) throws ProException {
        if (password == null || teacherId == null) {
            throw new ProException("请输入密码或账号！");
        }
        Map<String, Object> map = new HashMap<>(4);
        User user = userMapper.selectUserByPasswordAndId(password, teacherId);
        if (user != null) {
            //设置随机的、唯一的token保存到数据库种
            user.setToken(TokenUtils.getToken());
            if (userMapper.updateTokenByUser(user) > 0) {
                map.put("userId", user.getUserId());
                map.put("userName", user.getUserName());
                map.put("token", user.getToken());
                map.put("userType", user.getUserType());
            }
        }else {
            throw new ProException("密码或账号错误！");
        }

        return map;
    }
}
