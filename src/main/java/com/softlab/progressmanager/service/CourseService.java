package com.softlab.progressmanager.service;

import com.softlab.progressmanager.common.ProException;
import com.softlab.progressmanager.common.RestData;
import com.softlab.progressmanager.core.model.Coordinate;
import com.softlab.progressmanager.core.model.Course;

import java.util.List;
import java.util.Map;

/**
 * @author gwx
 * @version 1.0
 * @className CourseService
 * @description 课程service层
 * @date 2020/3/11 19:48
 */
public interface CourseService {

    /**
     * 添加一个课程
     * @param course
     * @return
     * @throws ProException
     */
    RestData insertCourse(Course course) throws ProException;

    /**
     * 删除指定 id的课程
     * @param courseId
     * @return
     * @throws ProException
     */
    RestData deleteCourseById(int courseId) throws ProException;

    /**
     * 修改指定id的课程
     * @param courseId
     * @param course
     * @return
     * @throws ProException
     */
    RestData updateCourseById(int courseId, Course course) throws ProException;

    /**
     * 查看指定班级的所有课程
     * @param classId
     * @return
     * @throws ProException
     */
    List<Map<String, Object>> selectCourseByClassId(int classId) throws ProException;

    /**
     * 查找指定id的课程
     * @param courseId
     * @return
     * @throws ProException
     */
    Map<String, Object> selectCourseById(int courseId) throws ProException;

    /**
     * 计算课程进度
     * @param courseId
     * @return
     * @throws ProException
     */
    float calculateProgressById(int courseId) throws ProException;

    /**
     * 更新完成课时时间
     * @param increaseHours
     * @param courseId
     * @return
     * @throws ProException
     */
    RestData updateFinishHours(int increaseHours, int courseId) throws ProException;

    /**
     * 添加课程的坐标
     * @param coordinate
     * @return
     * @throws ProException
     */
    RestData insertCoordinate(Coordinate coordinate) throws ProException;

    /**
     * 删除课程表上的某个课程
     * @param x
     * @param y
     * @param courseId
     * @return
     * @throws ProException
     */
    RestData deleteCoordinate(int x, int y, int courseId) throws ProException;

    /**
     * 修改坐标
     * @param x
     * @param y
     * @param coordinate
     * @return
     * @throws ProException
     */
    RestData updateCoordinate(int x, int y, Coordinate coordinate) throws ProException;

    /**
     * 查看指定课程id的课程表上的信息
     * @param courseId
     * @return
     * @throws ProException
     */
    List<Map<String, Object>> selectCoordinateByCourseId(int courseId) throws ProException;
}
