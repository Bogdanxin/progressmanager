package com.softlab.progressmanager.core.model;

import lombok.Data;

/**
 * @author gwx
 * @version 1.0
 * @className Absence
 * @description 未签到类，是记录一个学生的未签到的类
 * @date 2020/3/13 19:45
 */
@Data
public class Absence {

    /**
     * 缺席的学生id
     */
    private int studentId;

    /**
     * 缺席课程
     */
    private int courseId;

    /**
     * 签到时间
     */
    private String createTime;

}
