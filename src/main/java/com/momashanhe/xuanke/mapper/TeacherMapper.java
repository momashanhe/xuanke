package com.momashanhe.xuanke.mapper;

import com.momashanhe.xuanke.entity.Teacher;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface TeacherMapper {
    /**
     * 根据用户名查询教师
     *
     * @param username 用户名
     * @return 教师对象
     */
    Teacher findByUsername(@Param("username") String username);

    /**
     * 根据ID查询教师
     *
     * @param id 教师ID
     * @return 教师对象
     */
    Teacher findById(@Param("id") Integer id);

    /**
     * 获取所有教师列表
     *
     * @return 教师列表
     */
    List<Teacher> list();

    /**
     * 插入教师
     *
     * @param teacher 教师信息
     */
    void insert(Teacher teacher);

    /**
     * 根据ID更新教师
     *
     * @param teacher 教师信息
     */
    void updateById(Teacher teacher);

    /**
     * 根据ID删除教师（逻辑删除）
     *
     * @param id 教师ID
     */
    void deleteById(@Param("id") Integer id);
}