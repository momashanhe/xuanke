package com.momashanhe.xuanke.service;

import com.momashanhe.xuanke.entity.Course;

import java.util.List;

public interface CourseService {
    /**
     * 根据ID查询课程
     *
     * @param id 课程ID
     * @return 课程对象
     */
    Course findById(Integer id);

    /**
     * 获取所有课程列表
     *
     * @return 课程列表
     */
    List<Course> list();

    /**
     * 根据教师ID查询课程列表
     *
     * @param teacherId 教师ID
     * @return 课程列表
     */
    List<Course> listByTeacherId(Integer teacherId);

    /**
     * 添加课程
     *
     * @param course 课程信息
     */
    void add(Course course);

    /**
     * 更新课程
     *
     * @param course 课程信息
     */
    void updateById(Course course);

    /**
     * 删除课程（逻辑删除）
     *
     * @param id 课程ID
     */
    void deleteById(Integer id);
}