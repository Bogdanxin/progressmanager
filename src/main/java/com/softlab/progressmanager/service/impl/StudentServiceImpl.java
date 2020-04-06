package com.softlab.progressmanager.service.impl;

import com.softlab.progressmanager.common.ProException;
import com.softlab.progressmanager.common.RestData;
import com.softlab.progressmanager.core.mapper.AbsenceMapper;
import com.softlab.progressmanager.core.mapper.ClassMapper;
import com.softlab.progressmanager.core.mapper.StudentMapper;
import com.softlab.progressmanager.core.model.Student;
import com.softlab.progressmanager.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author gwx
 * @version 1.0
 * @className StudentServiceImpl
 * @description 实现StudentService接口
 * @date 2020/3/14 9:28
 */
@Service
public class StudentServiceImpl implements StudentService {

    private final StudentMapper studentMapper;
    private final AbsenceMapper absenceMapper;
    private final ClassMapper classMapper;

    @Autowired
    public StudentServiceImpl(StudentMapper studentMapper, AbsenceMapper absenceMapper, ClassMapper classMapper) {
        this.studentMapper = studentMapper;
        this.absenceMapper = absenceMapper;
        this.classMapper = classMapper;
    }


    @Override
    public RestData insertStudent(Student student, int classId) throws ProException {
        //查看是否已经又该学生的信息，有的话返回错误信息
        if (studentMapper.selectStudentById(student.getStudentId(), classId) != null) {
            throw new ProException("添加失败！已经有该学生的信息！");
        }

        if (studentMapper.insertStudent(student) > 0) {
            return new RestData(0,"添加成功！");
        }else {
            throw new ProException("添加失败！");
        }
    }

    @Override
    public List<Map<Integer, String>> insertStudents(List<Student> students) throws ProException {

        List<Map<Integer, String>> al = new ArrayList<>();
        for (Student student : students){
            Map<Integer, String> map = new HashMap<>(1);
            //判断这里的学生是不是本班
            if (studentMapper.selectStudentById(student.getStudentId(), student.getClassId()) != null) {
                map.put(student.getStudentId(), "该学生已经录入，无需再次记录。");
                al.add(map);
                continue;
            }
            if (studentMapper.insertStudent(student) > 0) {
                map.put(student.getStudentId(), "录入成功！");
            }else {
                map.put(student.getStudentId(), "录入失败！");
            }
            al.add(map);
        }
        return al;
    }

    @Override
    public RestData deleteStudentById(int studentId, int classId) throws ProException {

        //删除一个学生，不仅要删除本班的学生还要删除学生相应的签到情况
        if (studentMapper.deleteStudentById(studentId, classId) > 0
                && absenceMapper.deleteAbsenceByStudentId(studentId) > 0) {
            return new RestData(0,"删除成功!");
        }else {
            throw new ProException("删除失败！");
        }
    }

    @Override
    public RestData updateStudentById(int studentId, int classId, Student student) throws ProException {

        if (studentMapper.updateStudentById(studentId, classId, student) > 0) {
            return new RestData(0,"修改成功！");
        }else {
            throw new ProException("修改失败!");
        }
    }

    @Override
    public Map<String, Object> selectStudentById(int studentId, int classId) throws ProException {
         Map<String, Object> map = new HashMap<>();
         Student student = studentMapper.selectStudentById(studentId, classId);
        if (student != null) {
            map.put("studentId",student.getStudentId());
            map.put("studentName", student.getStudentName());
            map.put("absenceTimes", student.getAbsenceTimes());
        }else {
            throw new ProException("查找失败！");
        }

        return map;
    }

    @Override
    public List<Map<String, Object>> selectStudentsByClassId(int classId) throws ProException {
        List<Map<String, Object>> al = new ArrayList<>();
        List<Student> students = studentMapper.selectStudentsByClassId(classId);
        if (students != null && students.size() > 0) {
            for (Student student : students){
                Map<String, Object> map = new HashMap<>();
                map.put("studentId",student.getStudentId());
                map.put("studentName", student.getStudentName());
                map.put("absenceTimes", student.getAbsenceTimes());
                al.add(map);
            }
        }else {
            throw new ProException("查找失败！");
        }
        return al;
    }
}
