package com.softlab.progressmanager.common.utils;

import com.softlab.progressmanager.core.mapper.UserMapper;
import com.softlab.progressmanager.core.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import java.util.UUID;

/**
 * @author gwx
 * @version 1.0
 * @program TokenUtils
 * @description token管理类
 * @date 2020/3/11 15:37
 */
@Component
public class TokenUtils {

    private static UserMapper userMapper;

    @Autowired
    public TokenUtils(UserMapper userMapper){
        TokenUtils.userMapper = userMapper;
    }

    /**
     * 生成随机token
     * @return
     */
    public static String getToken(){
        return UUID.randomUUID().toString().replace("-", "");
    }

    /**
     * 根据token获取指定用户
     * @param request
     * @return
     */
    public static User getUserByToken(HttpServletRequest request){
        String token = request.getHeader("token");
        User user = null;

        if (token != null) {
            user = userMapper.selectUserByToken(token);
        }
        return user;
    }
}
