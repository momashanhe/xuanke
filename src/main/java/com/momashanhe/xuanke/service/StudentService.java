package com.momashanhe.xuanke.service;

import com.momashanhe.xuanke.entity.Student;

import java.util.List;

public interface StudentService {
    /**
     * 学生登录
     *
     * @param username 用户名
     * @param password 密码
     * @return 学生对象，登录失败返回null
     */
    Student login(String username, String password);

    /**
     * 根据ID查询学生
     *
     * @param id 学生ID
     * @return 学生对象
     */
    Student findById(Integer id);

    /**
     * 获取所有学生列表
     *
     * @return 学生列表
     */
    List<Student> list();

    /**
     * 添加学生
     *
     * @param student 学生信息
     */
    void add(Student student);

    /**
     * 更新学生
     *
     * @param student 学生信息
     */
    void updateById(Student student);

    /**
     * 删除学生（逻辑删除）
     *
     * @param id 学生ID
     */
    void deleteById(Integer id);
}