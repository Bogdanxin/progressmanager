package com.softlab.progressmanager.core.mapper;

import com.softlab.progressmanager.core.model.Coordinate;
import com.softlab.progressmanager.core.model.Course;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author gwx
 * @version 1.0
 * @program Course
 * @description course操作数据库方法
 * @date 2020/3/11 15:01
 */
@Mapper
@Repository
public interface CourseMapper {

    /**
     * 添加一个课程
     * @param course
     * @return
     */
    int insertCourse(Course course);

    /**
     * 删除指定id的课程
     * @param courseId
     * @return
     */
    int deleteCourseById(int courseId);

    /**
     * 修改指定id的课程
     * @param courseId
     * @param course
     * @return
     */
    int updateCourseById(@Param("courseId") int courseId,
                         @Param("course") Course course);

    /**
     * 查找指定班级的课程
     * @param classId
     * @return
     */
    List<Course> selectCourseByClassId(int classId);

    /**
     * 查找指定id的课程（详情）
     * @param courseId
     * @return
     */
    Course selectCourseById(int courseId);

    /**
     * 查看指定id的进程
     * @param courseId
     * @return
     */
    float calculateProgressById(int courseId);

    /**
     * 记录课时进度
     * @param increaseHours
     * @param courseId
     * @return
     */
    int updateFinishHours(@Param("increaseHours") int increaseHours,
                          @Param("courseId") int courseId);

    /**
     * 单独创建一个坐标表，代表一节在课程表中的课
     * @param coordinate
     * @return
     */
    int insertCoordinate(Coordinate coordinate);

    /**
     * 删除指定课表的课程
     * @param x
     * @param y
     * @param courseId
     * @return
     */
    int deleteCoordinate(@Param("x") int x,
                         @Param("y") int y,
                         @Param("courseId") int courseId);

    /**
     * 修改指定的课程表内容
     * @param x
     * @param y
     * @param coordinate
     * @return
     */
    int updateCoordinate(@Param("x") int x,
                         @Param("y") int y,
                         @Param("coordinate") Coordinate coordinate);

    /**
     * 查找指定课程的所有课表
     * @param courseId
     * @return
     */
    List<Coordinate> selectCoordinateByCourseId(int courseId);

}
