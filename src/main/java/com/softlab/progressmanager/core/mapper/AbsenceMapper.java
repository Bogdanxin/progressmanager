package com.softlab.progressmanager.core.mapper;

import com.softlab.progressmanager.core.model.Absence;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author gwx
 * @version 1.0
 * @className AbsenceMapper
 * @description 记录缺席，操作数据库
 * @date 2020/3/13 21:00
 */
@Mapper
@Repository
public interface AbsenceMapper {

    /**
     * 记录缺席
     * @param absence
     * @return
     */
    int insertAbsence(Absence absence);

    /**
     * 删除一个签到
     * @param studentId
     * @param courseId
     * @param date
     * @return
     */
    int deleteAbsenceById(@Param("studentId") int studentId,
                          @Param("courseId") int courseId,
                          @Param("date") String date);

    /**
     * 查看指定课程的签到
     * @param courseId
     * @return
     */
    List<Absence> selectAbsenceByCourseId(int courseId);

    /**
     * 查看指定日期的签到
     * @param createTime
     * @return
     */
    List<Absence> selectAbsenceByDate(String createTime);

    /**
     * 查找指定某日某节课的签到
     * @param courseId
     * @param date
     * @return
     */
    List<Absence> selectAbsenceByDateAndCourseId
    (@Param("courseId") int courseId,
     @Param("createTime") String date);

    /**
     * 查找该学生的本课程所有的签到记录
     * @param studentId
     * @param courseId
     * @return
     */
    List<Absence> selectAbsenceStudent(@Param("studentId") int studentId,
                                       @Param("courseId") int courseId);

    /**
     * 查找指定某日，某课，某人的签到情况
     * @param studentId
     * @param courseId
     * @param date
     * @return
     */
    Absence selectByDateAndStudentIdAndCourseId
    (@Param("studentId") int studentId,
     @Param("courseId") int courseId,
     @Param("date") String date);

    /**
     * 获取指定id的和时间的课程签到情况
     * @param courseId
     * @param date
     * @return
     */
    int getStudentAbsenceNum(@Param("courseId") int courseId,
                             @Param("createTime") String date);

    /**
     * 删除指定id的学生的所有签到
     * @param studentId
     * @return
     */
    int deleteAbsenceByStudentId(int studentId);
}
