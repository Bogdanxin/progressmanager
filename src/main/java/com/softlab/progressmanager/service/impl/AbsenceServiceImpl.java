package com.softlab.progressmanager.service.impl;

import com.softlab.progressmanager.common.ProException;
import com.softlab.progressmanager.common.RestData;
import com.softlab.progressmanager.core.mapper.AbsenceMapper;
import com.softlab.progressmanager.core.mapper.CourseMapper;
import com.softlab.progressmanager.core.mapper.StudentMapper;
import com.softlab.progressmanager.core.model.Absence;
import com.softlab.progressmanager.core.model.Student;
import com.softlab.progressmanager.service.AbsenceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author gwx
 * @version 1.0
 * @className AbsenceServiceImpl
 * @description 实现AbsenceService接口，主要负责签到
 * @date 2020/3/14 9:50
 */
@Service
public class AbsenceServiceImpl implements AbsenceService {

    private final AbsenceMapper absenceMapper;
    private final StudentMapper studentMapper;
    private final CourseMapper courseMapper;

    @Autowired
    public AbsenceServiceImpl(AbsenceMapper absenceMapper, StudentMapper studentMapper, CourseMapper courseMapper) {
        this.absenceMapper = absenceMapper;
        this.studentMapper = studentMapper;
        this.courseMapper = courseMapper;
    }


    @Override
    public RestData insertAbsence(Absence absence, int classId) throws ProException {
        //首先判断签到这个学生是不是这个在这个班级里
        Student student = studentMapper
                .selectStudentById(absence.getStudentId(), classId);
        if (student == null) {
            throw new ProException("该学生不存在，请确认后添加！");
        }

        //如果存在，则将该记录添加到记录表
        //添加成功，则返回成功，不然就报错
        if (absenceMapper.insertAbsence(absence) > 0
                && studentMapper.absenceStudent(absence.getStudentId(), classId) > 0) {
            return new RestData(0, "记录成功！");
        }else {
            throw new ProException("出现错误，记录失败");
        }
    }

    @Override
    public List<Map<Integer, String>>
        insertAbsences(List<Absence> absences, int classId) throws ProException {
        List<Map<Integer, String>> al = new ArrayList<>();
        for (Absence absence : absences){
            Map<Integer, String> map = new HashMap<>();

            Student student = studentMapper
                    .selectStudentById(absence.getStudentId(), classId);
            if (student == null) {
                map.put(absence.getStudentId(), "该学生不存在，请先录入学生信息后再记录。");
                al.add(map);
                continue;
            }

            if (student.getClassId() != classId) {
                map.put(student.getStudentId(), "该学生信息不存在于班级，请检查后再试。");
            }else {
                if (absenceMapper.insertAbsence(absence) > 0
                        && studentMapper.absenceStudent(student.getStudentId(), classId) > 0) {
                    map.put(student.getStudentId(), "添加成功！");
                }else {
                    map.put(student.getStudentId(), "出现错误，添加失败！");
                }
            }
            al.add(map);

        }
        return al;
    }

    @Override
    public RestData deleteAbsence
            (int studentId, int courseId, String date, int classId) throws ProException {


        if (absenceMapper.deleteAbsenceById(studentId, courseId, date) > 0
                && studentMapper.absenceStudentLift(studentId, classId) > 0) {
            return new RestData(0, "删除成功！");
        }else {
            throw new ProException("删除失败！");
        }
    }

    @Override
    public Map<String, Object> selectByDateStudentIdAndCourseId
            (int studentId, int courseId, String date) throws ProException {

        Map<String , Object> map = new HashMap<>();
        Absence absence = absenceMapper.
                selectByDateAndStudentIdAndCourseId(studentId, courseId, date);
        if (absence != null ) {
            map.put("studentId", absence.getStudentId());
            map.put("courseId",absence.getCourseId());
            map.put("createTime", absence.getCreateTime());
        }else {
            throw new ProException("出现错误，查找失败！");
        }

        return map;
    }

    @Override
    public List<Map<String, Object>> selectAbsenceByDate(String date) throws ProException {
        List<Map<String, Object>> al = new ArrayList<>();

        List<Absence> absences = absenceMapper.selectAbsenceByDate(date);
        if (absences != null && absences.size() > 0) {
            for (Absence absence : absences){
                Map<String, Object> map = new HashMap<>();
                map.put("studentId",absence.getStudentId());
                map.put("courseId", absence.getCourseId());
                map.put("createTime", absence.getCreateTime());
                al.add(map);
            }
        }else {
            throw new ProException("出现错误，查找失败！");
        }

        return al;
    }

    @Override
    public List<Map<String, Object>> selectAbsenceByCourseId(int courseId) throws ProException {
        List<Map<String, Object>> al = new ArrayList<>();

        List<Absence> absences = absenceMapper.selectAbsenceByCourseId(courseId);

        if (absences != null && absences.size() > 0) {
            for (Absence absence : absences){
                Map<String, Object> map = new HashMap<>();
                map.put("studentId",absence.getStudentId());
                map.put("courseId", absence.getCourseId());
                map.put("createTime", absence.getCreateTime());
                al.add(map);
            }
        }else {
            throw new ProException("出现错误，查找失败！");
        }

        return al;
    }

    @Override
    public List<Map<String, Object>> selectAbsenceByDateAndCourseId(int courseId, String date, int classId) throws ProException {
        List<Map<String, Object>> al = new ArrayList<>();

        List<Absence> absences = absenceMapper.selectAbsenceByDateAndCourseId(courseId, date);
        if (absences != null && absences.size() > 0) {
            for (Absence absence : absences){
                Map<String, Object> map = new HashMap<>();
                map.put("studentId",absence.getStudentId());
                map.put("courseId", absence.getCourseId());
                map.put("createTime", absence.getCreateTime());
                map.put("studentName", studentMapper.selectStudentById(absence.getStudentId(), classId).getStudentName());
                al.add(map);
            }
        }else {
            throw new ProException("出现错误，查找失败！");
        }

        return al;
    }

    @Override
    public List<Map<String, Object>> selectAbsenceStudent(int courseId, int studentId, int classId) throws ProException {
        List<Map<String, Object>> al = new ArrayList<>();

        List<Absence> absences = absenceMapper.selectAbsenceStudent(studentId, courseId);
        if (absences != null && absences.size() > 0) {
            for (Absence absence : absences){
                Map<String, Object> map = new HashMap<>(4);
                map.put("studentId",absence.getStudentId());
                map.put("courseId", absence.getCourseId());
                map.put("createTime", absence.getCreateTime());
                map.put("studentName", studentMapper.selectStudentById(studentId, classId).getStudentName());
                al.add(map);
            }
        }else {
            throw new ProException("出现错误，查找失败！");
        }

        return al;
    }
}
