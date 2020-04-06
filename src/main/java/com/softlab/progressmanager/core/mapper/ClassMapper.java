package com.softlab.progressmanager.core.mapper;

import com.softlab.progressmanager.core.model.CClass;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author gwx
 * @version 1.0
 * @describe 班级类的数据库操作
 * @date 2020/3/21 9:04
 */
@Mapper
@Repository
public interface ClassMapper {

    /**
     * 添加一个班级
     * @param cClass
     * @return
     */
    int insertClass(CClass cClass);


    /**
     * 删除课程对应的班级和血学生
     * @param classId
     * @return
     */
    int deleteClassById(int classId);

    /**
     * 修改指定id的class
     * @param cClass
     * @param classId
     * @return
     */
    int updateClass(@Param("class") CClass cClass,
                    @Param("classId") int classId);

    /**
     * 查找指定班级id的学生
     * @param classId
     * @return
     */
    CClass selectClassById(int classId);

    /**
     * 查看老师的所有班级
     * @param userId
     * @return
     */
    List<CClass> selectClassByUserId(int userId);

}
