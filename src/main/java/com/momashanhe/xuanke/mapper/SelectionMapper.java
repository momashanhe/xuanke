package com.momashanhe.xuanke.mapper;

import com.momashanhe.xuanke.entity.Selection;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface SelectionMapper {
    /**
     * 根据学生ID查询选课记录列表
     *
     * @param studentId 学生ID
     * @return 选课记录列表
     */
    List<Selection> listByStudentId(@Param("studentId") Integer studentId);

    /**
     * 根据学生ID和课程ID统计选课记录数量
     *
     * @param studentId 学生ID
     * @param courseId 课程ID
     * @return 选课记录数量
     */
    Integer countByStudentIdAndCourseId(@Param("studentId") Integer studentId, @Param("courseId") Integer courseId);

    /**
     * 根据课程ID统计选课人数
     *
     * @param courseId 课程ID
     * @return 选课人数
     */
    Integer countByCourseId(@Param("courseId") Integer courseId);

    /**
     * 插入选课记录
     *
     * @param selection 选课记录信息
     */
    void insert(Selection selection);

    /**
     * 根据ID删除选课记录
     *
     * @param id 选课记录ID
     */
    void deleteById(@Param("id") Integer id);

    /**
     * 根据课程ID删除选课记录
     *
     * @param courseId 课程ID
     */
    void deleteByCourseId(@Param("courseId") Integer courseId);

    /**
     * 根据课程ID列表删除选课记录
     *
     * @param courseIdList 课程ID列表
     */
    void deleteByCourseIdList(@Param("courseIdList") List<Integer> courseIdList);

    /**
     * 根据学生ID删除选课记录
     *
     * @param studentId 学生ID
     */
    void deleteByStudentId(@Param("studentId") Integer studentId);
}