package com.softlab.progressmanager.core.mapper;

import com.softlab.progressmanager.core.model.Student;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author gwx
 * @version 1.0
 * @className StudentMapper
 * @description 学生类对数据库操作
 * @date 2020/3/13 19:56
 */
@Mapper
@Repository
public interface StudentMapper {

    /**
     * 添加一个学生
     * @param student
     * @return
     */
    int insertStudent(Student student);

    /**
     * 删除指定id一个学生
     * @param studentId
     * @param classId
     * @return
     */
    int deleteStudentById(@Param("studentId") int studentId,
                          @Param("classId") int classId);

    /**
     * 修改一个指定id的student
     * @param studentId
     * @param student
     * @param classId
     * @return
     */
    int updateStudentById(@Param("studentId") int studentId,
                          @Param("classId") int classId,
                          @Param("student") Student student);

    /**
     * 查找指定id的学生
     * @param studentId
     * @param classId
     * @return
     */
    Student selectStudentById(@Param("studentId") int studentId,
                              @Param("classId") int classId);

    /**
     * 缺席学生记录
     * @param studentId
     * @param classId
     * @return
     */
    int absenceStudent(@Param("studentId") int studentId,
                       @Param("classId") int classId);

    /**
     * 解除一次缺席
     * @param studentId
     * @param classId
     * @return
     */
    int absenceStudentLift(@Param("studentId") int studentId,
                           @Param("classId") int classId);

    /**
     * 查看指定班级的所有学生
     * @param classId
     * @return
     */
    List<Student> selectStudentsByClassId(int classId);

}
