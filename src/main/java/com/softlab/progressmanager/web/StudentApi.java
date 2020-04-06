package com.softlab.progressmanager.web;

import com.softlab.progressmanager.common.ProException;
import com.softlab.progressmanager.common.RestData;
import com.softlab.progressmanager.common.utils.JsonUtils;
import com.softlab.progressmanager.common.utils.VerifyUtil;
import com.softlab.progressmanager.core.model.Student;
import com.softlab.progressmanager.service.StudentService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * @author gwx
 * @version 1.0
 * @className StudentApi
 * @description 学生类的web层
 * @date 2020/3/14 14:31
 */
@RestController
@CrossOrigin(origins = "*")
public class StudentApi {

    private final static Logger logger = LoggerFactory.getLogger(StudentApi.class);
    private final StudentService studentService;

    @Autowired
    public StudentApi(StudentService studentService) {
        this.studentService = studentService;
    }

    @PostMapping(value = "/addStudent")
    public RestData addStudent(@RequestBody Student student,
                               @RequestParam("classId") int classId,
                               HttpServletRequest request){
        logger.info("add student :" + JsonUtils.getJsonFromObj(student));

        if (VerifyUtil.verifyUserType(request) != 0) {
            return new RestData(1, "用户未授权！");
        }
        try {
            return studentService.insertStudent(student, classId);
        }catch (ProException ex){
            return new RestData(1, ex.getMessage());
        }
    }

    @PostMapping(value = "/addStudents")
    public RestData addStudents(@RequestBody List<Student> students,
                                HttpServletRequest request){
        logger.info("add students by list: " + JsonUtils.getJsonFromObj(students));
        if (VerifyUtil.verifyUserType(request) != 0) {
            return new RestData(1, "用户未授权！");
        }

        try {
            return new RestData(studentService.insertStudents(students));
        }catch (ProException ex){
            return new RestData(1, ex.getMessage());
        }
    }

    @DeleteMapping(value = "/deleteStudent")
    public RestData deleteStudent(@RequestParam("studentId") int studentId,
                                  @RequestParam("classId") int classId,
                                  HttpServletRequest request){
        logger.info("delete student by id:" + studentId);

        if (VerifyUtil.verifyUserType(request) != 0) {
            return new RestData(1,"用户未授权！");
        }
        try {
            return studentService.deleteStudentById(studentId, classId);
        }catch (ProException ex){
            return new RestData(1, ex.getMessage());
        }
    }

    @PostMapping(value = "/updateStudent")
    public RestData updateStudentById(@RequestParam("studentId") int studentId,
                                      @RequestParam("classId") int classId,
                                      @RequestBody Student student,
                                      HttpServletRequest request){
        logger.info("update student by id : " + studentId);
        if (VerifyUtil.verifyUserType(request) != 0) {
            return new RestData(1,"用户未授权！");
        }

        try {
            return studentService.updateStudentById(studentId, classId, student);
        }catch (ProException ex){
            return new RestData(1, ex.getMessage());
        }
    }

    @GetMapping(value = "/getStudentById")
    public RestData selectStudentById(@RequestParam("studentId") int studentId,
                                      @RequestParam("classId") int classId,
                                      HttpServletRequest request){
        logger.info("get student by id :" + studentId);
        if (VerifyUtil.verifyUserType(request) != 0) {
            return new RestData(1, "用户未授权！");
        }

        try {
            return new RestData(studentService.selectStudentById(studentId, classId));
        }catch (ProException ex){
            return new RestData(1, ex.getMessage());
        }
    }

    @GetMapping(value = "/getStudentByClassId")
    public RestData selectStudentByClassId(@RequestParam("classId") int classId,
                                           HttpServletRequest request){
        logger.info("get student by id :" + classId);
        if (VerifyUtil.verifyUserType(request) != 0) {
            return new RestData(1, "用户未授权！");
        }

        try {
            return new RestData(studentService.selectStudentsByClassId(classId));
        }catch (ProException ex){
            return new RestData(1, ex.getMessage());
        }
    }


}
