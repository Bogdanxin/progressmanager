package com.softlab.progressmanager.web;

import com.softlab.progressmanager.common.ProException;
import com.softlab.progressmanager.common.RestData;
import com.softlab.progressmanager.common.utils.JsonUtils;
import com.softlab.progressmanager.common.utils.VerifyUtil;
import com.softlab.progressmanager.core.model.Coordinate;
import com.softlab.progressmanager.core.model.Course;
import com.softlab.progressmanager.service.impl.CourseServiceImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

/**
 * @author gwx
 * @version 1.0
 * @className CourseApi
 * @description 课程web层
 * @date 2020/3/12 16:07
 */
@RestController
@CrossOrigin(origins = "*")
public class CourseApi {

    private final static Logger logger = LoggerFactory.getLogger(CourseApi.class);
    private final CourseServiceImpl courseService;

    @Autowired
    public CourseApi(CourseServiceImpl courseService) {
        this.courseService = courseService;
    }

    @PostMapping(value = "/addCourse")
    public RestData addCourse(@RequestBody Course course,
                              HttpServletRequest request){
        logger.info("add course: " + JsonUtils.getJsonFromObj(course));

        if (VerifyUtil.verifyUserType(request) != 0) {
            return new RestData(1,"用户未授权！");
        }

        try {
            return courseService.insertCourse(course);
        }catch (ProException ex){
            return new RestData(1,ex.getMessage());
        }
    }

    @DeleteMapping(value = "/deleteCourseById")
    public RestData deleteCourseById(@RequestParam("courseId") int id,
                                     HttpServletRequest request){
        logger.info("delete course by id " + id);
        if (VerifyUtil.verifyUserType(request) != 0) {
            return new RestData(1,"用户未授权！");
        }

        try {
            return courseService.deleteCourseById(id);
        }catch (ProException ex){
            return new RestData(1,ex.getMessage());
        }
    }

    @PostMapping(value = "/updateCourseById")
    public RestData updateCourseById(@RequestParam("courseId") int id,
                                     @RequestBody Course course,
                                     HttpServletRequest request){
        logger.info("update course"+ JsonUtils.getJsonFromObj(course) + "by id : " + id);

        if (VerifyUtil.verifyUserType(request) != 0) {
            return new RestData(1,"用户未授权！");
        }
        try {
            return courseService.updateCourseById(id, course);
        }catch (ProException ex){
            return new RestData(1, ex.getMessage());
        }
    }

    @GetMapping(value = "/getCourseByClassId")
    public RestData getCourseByClassId(@RequestParam("classId") int classId,
                                       HttpServletRequest request){
        logger.info("get course by class id : " + classId);

        if (VerifyUtil.verifyUserType(request) != 0) {
            return new RestData(1,"用户未授权！");
        }
        try {
            return new RestData(courseService.selectCourseByClassId(classId));
        }catch (ProException ex){
            return new RestData(1, ex.getMessage());
        }
    }

    @GetMapping(value = "/getCourseById")
    public RestData getCourseById(@RequestParam("courseId") int id,
                                  HttpServletRequest request){
        logger.info("get course by id : " + id);
        if (VerifyUtil.verifyUserType(request) != 0) {
            return new RestData(1,"用户未授权！");
        }
        try {
            return new RestData(courseService.selectCourseById(id));
        }catch (ProException ex){
            return new RestData(1, ex.getMessage());
        }
    }

    @GetMapping(value = "/getCalculation/{id}")
    public RestData getCalculation(@PathVariable int id){
        logger.info("get calculation progress by id : " + id);

        try {
            return new RestData(courseService.calculateProgressById(id)*100 + "%");
        }catch (ProException ex){
            return new RestData(1, ex.getMessage());
        }
    }

    @PostMapping(value = "/updateHours/{id}")
    public RestData updateHours(@RequestParam("increaseHours") int increaseHours,
                                @PathVariable int id,
                                HttpServletRequest request){
        logger.info("update hours " + increaseHours);
        if (VerifyUtil.verifyUserType(request) != 0) {
            return new RestData(1,"用户未授权！");
        }

        try {
            return courseService.updateFinishHours(increaseHours, id);
        }catch (ProException ex){
            return new RestData(1, ex.getMessage());
        }
    }

    @PostMapping(value = "/addCoordinate")
    public RestData addCoordinate(@RequestBody Coordinate coordinate,
                                  HttpServletRequest request){
        logger.info("add coordinate: " + JsonUtils.getJsonFromObj(coordinate));
        if (VerifyUtil.verifyUserType(request) != 0) {
            return new RestData(1,"用户未授权！");
        }

        try {
            return courseService.insertCoordinate(coordinate);
        }catch (ProException ex){
            return new RestData(1, ex.getMessage());
        }
    }

    @DeleteMapping(value = "/deleteCoordinate")
    public RestData deleteCoordinate(@RequestParam("x") int x,
                                     @RequestParam("y") int y,
                                     @RequestParam("courseId") int courseId,
                                     HttpServletRequest request){
        logger.info("delete coordinate");
        if (VerifyUtil.verifyUserType(request) != 0) {
            return new RestData(1,"用户未授权！");
        }

        try {
            return courseService.deleteCoordinate(x, y, courseId);
        }catch (ProException ex){
            return new RestData(1, ex.getMessage());
        }
    }

    @PostMapping(value = "/updateCoordinate")
    public RestData updateCoordinate(@RequestBody Coordinate coordinate,
                                     @RequestParam("x") int x,
                                     @RequestParam("y") int y,
                                     HttpServletRequest request){
        logger.info("update coordinate: " + JsonUtils.getJsonFromObj(coordinate));
        if (VerifyUtil.verifyUserType(request) != 0) {
            return new RestData(1,"用户未授权！");
        }

        try {
            return courseService.updateCoordinate(x, y , coordinate);
        }catch (ProException ex){
            return new RestData(1, ex.getMessage());
        }
    }

    @GetMapping(value = "/getCoordinate")
    public RestData getCoordinate(@RequestParam("courseId") int courseId,
                                  HttpServletRequest request){
        logger.info("get coordinate by id :" + courseId);

        if (VerifyUtil.verifyUserType(request) != 0) {
            return new RestData(1, "用户未授权！");
        }

        try {
            return new RestData(courseService.selectCoordinateByCourseId(courseId));
        }catch (ProException ex){
            return new RestData(1, ex.getMessage());
        }
    }
}
