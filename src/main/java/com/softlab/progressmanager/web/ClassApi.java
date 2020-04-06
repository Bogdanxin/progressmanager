package com.softlab.progressmanager.web;

import com.softlab.progressmanager.common.ProException;
import com.softlab.progressmanager.common.RestData;
import com.softlab.progressmanager.common.utils.JsonUtils;
import com.softlab.progressmanager.common.utils.VerifyUtil;
import com.softlab.progressmanager.core.model.CClass;
import com.softlab.progressmanager.service.ClassService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

/**
 * @author gwx
 * @version 1.0
 * @describe 班级web层的操作
 * @date 2020/3/21 19:49
 */
@RestController
@CrossOrigin(origins = "*")
public class ClassApi {

    private final static Logger logger = LoggerFactory.getLogger(ClassApi.class);
    private final ClassService classService;

    @Autowired
    public ClassApi(ClassService classService) {
        this.classService = classService;
    }

    @PostMapping(value = "/addClass")
    public RestData addClass(@RequestBody CClass cClass,
                             HttpServletRequest request){
        logger.info("add class" + JsonUtils.getJsonFromObj(cClass));
        if (VerifyUtil.verifyUserType(request) != 0) {
            return new RestData(1, "用户未授权！");
        }
        try {
            return classService.insertClass(cClass);
        }catch (ProException ex){
            return new RestData(1, ex.getMessage());
        }
    }

    @PostMapping(value = "/updateClass")
    public RestData updateClass(@RequestBody CClass cClass,
                                @RequestParam("classId") int classId,
                                HttpServletRequest request){
        logger.info("update class by id : " + classId);
        if (VerifyUtil.verifyUserType(request) != 0) {
            return new RestData(1, "用户未授权！");
        }
        try {
            return classService.updateClass(cClass, classId);
        }catch (ProException ex){
            return new RestData(1, ex.getMessage());
        }

    }

    @DeleteMapping(value = "/deleteById")
    public RestData deleteClassById(@RequestParam("classId") int classId,
                                    @RequestParam("courseId") int courseId,
                                    HttpServletRequest request){
        logger.info("delete class by id :" + classId);

        if (VerifyUtil.verifyUserType(request) != 0) {
            return new RestData(1, "用户未授权！");
        }

        try {
            return classService.deleteClassById(classId, courseId);
        }catch (ProException ex){
            return new RestData(1, ex.getMessage());
        }
    }

    @GetMapping(value = "/getClassById")
    public RestData getClassById(@RequestParam("classId") int classId,
                                 HttpServletRequest request){
        logger.info("get class by id : " + classId);

        if (VerifyUtil.verifyUserType(request) != 0) {
            return new RestData(1, "用户未授权！");
        }

        try {
            return new RestData(classService.selectClassById(classId));
        }catch (ProException ex){
            return new RestData(1, ex.getMessage());
        }
    }

    @GetMapping(value = "/getClassesByUserId")
    public RestData getClassByTeacherId(@RequestParam("userId") int userId,
                                        HttpServletRequest request){

        logger.info("get class by id : " + userId);

        if (VerifyUtil.verifyUserType(request) != 0) {
            return new RestData(1, "用户未授权！");
        }

        try {
            return new RestData(classService.selectClassByUserId(userId));
        }catch (ProException ex){
            return new RestData(1, ex.getMessage());
        }
    }

    
}
