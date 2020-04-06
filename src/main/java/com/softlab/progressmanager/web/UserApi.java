package com.softlab.progressmanager.web;

import com.softlab.progressmanager.common.ProException;
import com.softlab.progressmanager.common.RestData;
import com.softlab.progressmanager.common.utils.JsonUtils;
import com.softlab.progressmanager.common.utils.TokenUtils;
import com.softlab.progressmanager.common.utils.VerifyUtil;
import com.softlab.progressmanager.core.model.User;
import com.softlab.progressmanager.service.impl.UserServiceImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

/**
 * @author gwx
 * @version 1.0
 * @className UserApi
 * @description 用户web层操作
 * @date 2020/3/12 15:09
 */
@RestController
@CrossOrigin(origins = "*")
public class UserApi {

    private final static Logger logger = LoggerFactory.getLogger(UserApi.class);

    private final UserServiceImpl userService;

    @Autowired
    public UserApi(UserServiceImpl userService) {
        this.userService = userService;
    }

    @DeleteMapping(value = "/deleteUserById")
    public RestData deleteUserById(@RequestParam("id") int id,
                                   HttpServletRequest request){
        logger.info("delete user by id : " + id);

        if (VerifyUtil.verifyUserType(request) != 1) {
            return new RestData(1,"用户未授权！");
        }

        try {
            return userService.deleteUserById(id);
        }catch (ProException ex){
            return new RestData(1,ex.getMessage());
        }
    }

    @PostMapping(value = "/register")
    public RestData addUser(@RequestBody User user){
        logger.info("add user :" + JsonUtils.getJsonFromObj(user));
        try {
            return userService.insertUser(user);
        }catch (ProException e){
            return new RestData(1, e.getMessage());
        }
    }

    @GetMapping(value = "/getUserById")
    public RestData getUserById(@RequestParam("id") int id,
                                HttpServletRequest request){
        logger.info("get user by id : "+id);
        if (VerifyUtil.verifyUserType(request) != 1) {
            return new RestData(1, "用户未授权！");
        }

        try {
            return new RestData(userService.selectUserById(id));
        }catch (ProException ex){
            return new RestData(1, ex.getMessage());
        }
    }

    @PostMapping(value = "/exit/{token}")
    public RestData exit(@PathVariable String token,
                         HttpServletRequest request){
        logger.info("exit user by token : " + token);

        if (VerifyUtil.verifyUserType(request) != 1) {
            return new RestData(1,"用户未授权！");
        }
        try {
            User user = TokenUtils.getUserByToken(request);
            return userService.updateTokenNullById(user.getUserId());
        }catch (ProException ex){
            return new RestData(1, ex.getMessage());
        }
    }

    @PostMapping(value = "/login")
    public RestData login(@RequestBody User user){

        logger.info("login by teacher id : " + user.getTeacherId());
        try {
            return new RestData(userService.selectUserByPasswordAndId(user.getPassword(), user.getTeacherId()));
        }catch (ProException ex){
            return new RestData(1, ex.getMessage());
        }
    }

}
