package com.softlab.progressmanager.core.model;

import lombok.Data;

/**
 * @author gwx
 * @version 1.0
 * @className Student
 * @description 学生类，主要用于记录缺席，要注意的是，
 * 这里的学生是对应的该课程下的班级，一旦没有这个课程了，学生也会删除
 * @date 2020/3/13 19:49
 */
@Data
public class Student {

    /**
     * 学生id，20********
     */
    private int studentId;

    /**
     * 学生姓名
     */
    private String studentName;

    /**
     * 缺席次数
     */
    private int absenceTimes;

    /**
     * 学生班级的id
     */
    private int classId;
}
