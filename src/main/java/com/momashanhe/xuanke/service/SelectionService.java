package com.momashanhe.xuanke.service;

import com.momashanhe.xuanke.entity.Selection;

import java.util.List;

public interface SelectionService {
    /**
     * 根据学生ID查询选课记录
     *
     * @param studentId 学生ID
     * @return 选课记录列表
     */
    List<Selection> listByStudentId(Integer studentId);

    /**
     * 学生选课
     *
     * @param studentId 学生ID
     * @param courseId 课程ID
     */
    void add(Integer studentId, Integer courseId);

    /**
     * 学生退课
     *
     * @param id 选课记录ID
     */
    void deleteById(Integer id);
}