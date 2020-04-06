package com.softlab.progressmanager.core.model;

import lombok.Data;

/**
 * @author gwx
 * @version 1.0
 * @program Course
 * @description 课程实体类
 * @date 2020/3/11 14:40
 */
@Data
public class Course {

    /**
     * 课程id，自增，用于查找
     */
    private int courseId;

    /**
     * 课程名称
     */
    private String courseName;

    /**
     * 课时总学时
     */
    private int courseHours;

    /**
     * 课程已完成学时
     */
    private int courseFinishHours;

    /**
     * 课程介绍
     */
    private String courseIntroduction;

    /**
     * 对应班级id
     */
    private int classId;

}
