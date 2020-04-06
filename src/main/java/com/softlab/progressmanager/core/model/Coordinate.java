package com.softlab.progressmanager.core.model;

import lombok.Data;

/**
 * @author gwx
 * @version 1.0
 * @describe 坐标类，用于标记课程表上的课程
 * @date 2020/3/24 16:07
 */
@Data
public class Coordinate {

    /**
     * 课程id
     */
    private int courseId;

    /**
     * x坐标
     */
    private int x;

    /**
     * y坐标
     */
    private int y;
}
