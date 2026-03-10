package com.momashanhe.xuanke.mapper;

import com.momashanhe.xuanke.entity.Student;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface StudentMapper {
    /**
     * 根据用户名查询学生
     *
     * @param username 用户名
     * @return 学生对象
     */
    Student findByUsername(@Param("username") String username);

    /**
     * 根据ID查询学生
     *
     * @param id 学生ID
     * @return 学生对象
     */
    Student findById(@Param("id") Integer id);

    /**
     * 获取所有学生列表
     *
     * @return 学生列表
     */
    List<Student> list();

    /**
     * 插入学生
     *
     * @param student 学生信息
     */
    void insert(Student student);

    /**
     * 根据ID更新学生
     *
     * @param student 学生信息
     */
    void updateById(Student student);

    /**
     * 根据ID删除学生（逻辑删除）
     *
     * @param id 学生ID
     */
    void deleteById(@Param("id") Integer id);
}