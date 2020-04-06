package com.softlab.progressmanager.service.impl;

import com.softlab.progressmanager.common.ProException;
import com.softlab.progressmanager.common.RestData;
import com.softlab.progressmanager.core.mapper.CourseMapper;
import com.softlab.progressmanager.core.model.Coordinate;
import com.softlab.progressmanager.core.model.Course;
import com.softlab.progressmanager.service.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author gwx
 * @version 1.0
 * @className CourseServiceImpl
 * @description 课程service层实现接口
 * @date 2020/3/11 20:24
 */
@Service
public class CourseServiceImpl implements CourseService {

    private final CourseMapper courseMapper;

    @Autowired
    public CourseServiceImpl(CourseMapper courseMapper) {
        this.courseMapper = courseMapper;
    }

    @Override
    public RestData insertCourse(Course course) throws ProException {
        if (courseMapper.insertCourse(course) > 0) {
            return new RestData(0,"添加成功！");
        }else {
            throw new ProException("添加失败！");
        }
    }

    @Override
    public RestData deleteCourseById(int courseId) throws ProException {
        if (courseMapper.deleteCourseById(courseId) > 0) {
            return new RestData(0, "删除成功！");
        }else {
            throw new ProException("删除失败！");
        }
    }

    @Override
    public RestData updateCourseById(int courseId, Course course) throws ProException {
        if (courseMapper.updateCourseById(courseId, course) > 0) {
            return new RestData(0, "修改成功!");
        }else {
            throw new ProException("修改失败！");
        }
    }

    @Override
    public List<Map<String, Object>> selectCourseByClassId(int classId) throws ProException {
        List<Map<String, Object>> al = new ArrayList<>();
        List<Course> courses = courseMapper.selectCourseByClassId(classId);
        if (courses != null && courses.size() > 0) {
            for (Course course : courses){
                Map<String, Object> map = new HashMap<>(6);
                map.put("courseId", course.getCourseId());
                map.put("courseName", course.getCourseName());
                map.put("courseHours", course.getCourseHours());
                map.put("courseFinishHours", course.getCourseFinishHours());
                map.put("courseIntroduction", course.getCourseIntroduction());
                map.put("classId", course.getClassId());
                al.add(map);
            }
        }else {
            throw new ProException("查找失败！");
        }

        return al;

    }

    @Override
    public Map<String, Object> selectCourseById(int courseId) throws ProException {
        Map<String, Object> map = new HashMap<>(8);
        Course course = courseMapper.selectCourseById(courseId);
        if (course != null) {
            map.put("courseId", course.getCourseId());
            map.put("courseName", course.getCourseName());
            map.put("courseHours", course.getCourseHours());
            map.put("courseFinishHours", course.getCourseFinishHours());
            map.put("courseIntroduction", course.getCourseIntroduction());
            map.put("classId", course.getClassId());
        }else {
            throw new ProException("查找失败！");
        }
        return map;
    }


    @Override
    public float calculateProgressById(int courseId) throws ProException {
        float progress = courseMapper.calculateProgressById(courseId);

        if (progress >= 0) {
            return progress;
        }else {
            throw new ProException("课程进度查看异常！");
        }
    }

    @Override
    public RestData updateFinishHours(int increaseHours, int courseId) throws ProException {
        if (increaseHours < 0) {
            throw new ProException("请输入正确的时长！");
        }

        if (courseMapper.updateFinishHours(increaseHours, courseId) > 0) {
            return new RestData(0, "修改成功！");
        }else {
            throw new ProException("修改失败！");
        }
    }

    @Override
    public RestData insertCoordinate(Coordinate coordinate) throws ProException {
        if (courseMapper.insertCoordinate(coordinate) > 0) {
            return new RestData(0, "添加成功！");
        }else {
            throw new ProException("添加失败！");
        }
    }

    @Override
    public RestData deleteCoordinate(int x, int y, int courseId) throws ProException {

        if (courseMapper.deleteCoordinate(x, y, courseId) > 0) {
            return new RestData(0, "删除成功！");
        }else {
            throw new ProException("删除失败！");
        }
    }

    @Override
    public RestData updateCoordinate(int x, int y, Coordinate coordinate) throws ProException {

        if (courseMapper.updateCoordinate(x, y, coordinate) > 0) {
            return new RestData(1, "修改成功！");
        }else {
            throw new ProException("修改失败！");
        }
    }

    @Override
    public List<Map<String, Object>> selectCoordinateByCourseId(int courseId) throws ProException {
        List<Map<String, Object>> al = new ArrayList<>();
        List<Coordinate> coordinates = courseMapper.selectCoordinateByCourseId(courseId);
        if (coordinates != null && coordinates.size() > 0) {
            for (Coordinate coordinate : coordinates){
                Map<String, Object> map = new HashMap<>(3);
                map.put("x", coordinate.getX());
                map.put("y", coordinate.getY());
                map.put("courseId", coordinate.getCourseId());
                al.add(map);
            }
        }else {
            throw new ProException("查找失败！");
        }

        return al;
    }
}
