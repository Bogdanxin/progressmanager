package com.softlab.progressmanager.service.impl;

import com.softlab.progressmanager.common.ProException;
import com.softlab.progressmanager.common.RestData;
import com.softlab.progressmanager.core.mapper.ClassMapper;
import com.softlab.progressmanager.core.mapper.CourseMapper;
import com.softlab.progressmanager.core.model.CClass;
import com.softlab.progressmanager.service.ClassService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author gwx
 * @version 1.0
 * @describe 实现班级service层操作的接口
 * @date 2020/3/21 15:58
 */
@Service
public class ClassServiceImpl implements ClassService {

    private final ClassMapper classMapper;
    private final CourseMapper courseMapper;

    @Autowired
    public ClassServiceImpl(ClassMapper classMapper, CourseMapper courseMapper) {
        this.classMapper = classMapper;
        this.courseMapper = courseMapper;
    }

    @Override
    public RestData insertClass(CClass cClass) throws ProException {
        if (classMapper.insertClass(cClass) > 0) {
            return new RestData(0, "添加成功！");
        }else {
            throw new ProException("添加失败！");
        }
    }

    @Override
    public RestData updateClass(CClass cClass, int classId) throws ProException {
        if (classMapper.updateClass(cClass, classId) > 0) {
            return new RestData(0, "修改成功！");
        }else {
            throw new ProException("修改失败！");
        }
    }

    @Override
    public RestData deleteClassById(int classId, int courseId) throws ProException {
        if (classMapper.deleteClassById(classId) > 0
                && courseMapper.deleteCourseById(courseId) > 0){
            return new RestData(0, "删除成功!");
        }else {
            throw new ProException("删除失败！");
        }
    }

    @Override
    public Map<String, Object> selectClassById(int classId) throws ProException {
        Map<String, Object> map = new HashMap<>();
        CClass cClass = classMapper.selectClassById(classId);
        if (cClass != null) {
            map.put("classId", cClass.getClassId());
            map.put("className", cClass.getClassName());
            map.put("classStudentName", cClass.getClassStudentNum());
            map.put("userId", cClass.getUserId());
        }else {
            throw new ProException("查找失败！");
        }

        return map;
    }

    @Override
    public List<Map<String, Object>> selectClassByUserId(int userId) throws ProException {
        List<Map<String, Object>> al = new ArrayList<>();
        List<CClass> cClasses = classMapper.selectClassByUserId(userId);
        if (cClasses != null && cClasses.size() > 0) {
            for (CClass cClass : cClasses){
                Map<String, Object> map = new HashMap<>();
                map.put("classId", cClass.getClassId());
                map.put("className", cClass.getClassName());
                map.put("classStudentName", cClass.getClassStudentNum());
                map.put("userId", cClass.getUserId());
                al.add(map);
            }
        }else {
            throw new ProException("查找失败！");
        }

        return al;
    }
}
