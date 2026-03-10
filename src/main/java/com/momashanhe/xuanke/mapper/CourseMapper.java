package com.momashanhe.xuanke.mapper;

import com.momashanhe.xuanke.entity.Course;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface CourseMapper {
    /**
     * 根据ID查询课程
     *
     * @param id 课程ID
     * @return 课程对象
     */
    Course findById(@Param("id") Integer id);

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
    List<Course> listByTeacherId(@Param("teacherId") Integer teacherId);

    /**
     * 插入课程
     *
     * @param course 课程信息
     */
    void insert(Course course);

    /**
     * 根据ID更新课程
     *
     * @param course 课程信息
     */
    void updateById(Course course);

    /**
     * 根据ID删除课程
     *
     * @param id 课程ID
     */
    void deleteById(@Param("id") Integer id);

    /**
     * 根据教师ID删除课程（逻辑删除）
     *
     * @param teacherId 教师ID
     */
    void deleteByTeacherId(@Param("teacherId") Integer teacherId);
}