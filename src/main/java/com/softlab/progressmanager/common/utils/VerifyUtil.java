package com.softlab.progressmanager.common.utils;

import com.softlab.progressmanager.core.mapper.UserMapper;
import com.softlab.progressmanager.core.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;

/**
 * @author gwx
 * @version 1.0
 * @program VerifyUtil
 * @description 验证用户类型类
 * @date 2020/3/11 14:33
 */
@Component
public class VerifyUtil {

    private static UserMapper userMapper;

    @Autowired
    public VerifyUtil(UserMapper userMapper) {
        VerifyUtil.userMapper = userMapper;
    }

    public static int verifyUserType(HttpServletRequest request){
        String token = request.getHeader("token");
        if (token == null) {
            return -1;
        }

        User user = userMapper.selectUserByToken(token);
        if (user != null) {
            return user.getUserType();
        }else {
            return 0;
        }
    }
}
