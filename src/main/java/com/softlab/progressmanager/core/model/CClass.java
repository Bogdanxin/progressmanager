package com.softlab.progressmanager.core.model;

import lombok.Data;

/**
 * @author gwx
 * @version 1.0
 * @describe 班级类
 * @date 2020/3/21 9:00
 */
@Data
public class CClass {

    /**
     * 班级id
     */
    private int classId;

    /**
     * 班级名称
     */
    private String className;

    /**
     * 班级学生数
     */
    private int classStudentNum;

    /**
     * 教师id
     */
    private int userId;
}
