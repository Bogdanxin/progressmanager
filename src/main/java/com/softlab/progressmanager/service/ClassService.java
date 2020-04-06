package com.softlab.progressmanager.service;

import com.softlab.progressmanager.common.ProException;
import com.softlab.progressmanager.common.RestData;
import com.softlab.progressmanager.core.model.CClass;

import java.util.List;
import java.util.Map;

/**
 * @author gwx
 * @version 1.0
 * @describe 班级类的service层接口
 * @date 2020/3/21 15:50
 */
public interface ClassService {

    /**
     * 添加一个班级
     * @param cClass
     * @return
     * @throws ProException
     */
    RestData insertClass(CClass cClass) throws ProException;

    /**
     * 修改指定id的班级
     * @param cClass
     * @param classId
     * @return
     * @throws ProException
     */
    RestData updateClass(CClass cClass, int classId) throws ProException;

    /**
     * 删除指定id的班级
     * @param classId
     * @param courseId
     * @return
     * @throws ProException
     */
    RestData deleteClassById(int classId, int courseId) throws ProException;

    /**
     * 查找指定id的班级
     * @param classId
     * @return
     * @throws ProException
     */
    Map<String, Object> selectClassById(int classId) throws ProException;

    /**
     * 查找指定教师的所有班级
     * @param userId
     * @return
     * @throws ProException
     */
    List<Map<String, Object>> selectClassByUserId(int userId) throws ProException;

}
