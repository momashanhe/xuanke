package com.momashanhe.xuanke.service;

import com.momashanhe.xuanke.entity.Teacher;

import java.util.List;

public interface TeacherService {
    /**
     * 教师登录
     *
     * @param username 用户名
     * @param password 密码
     * @return 教师对象，登录失败返回null
     */
    Teacher login(String username, String password);

    /**
     * 根据ID查询教师
     *
     * @param id 教师ID
     * @return 教师对象
     */
    Teacher findById(Integer id);

    /**
     * 获取所有教师列表
     *
     * @return 教师列表
     */
    List<Teacher> list();

    /**
     * 添加教师
     *
     * @param teacher 教师信息
     */
    void add(Teacher teacher);

    /**
     * 更新教师
     *
     * @param teacher 教师信息
     */
    void updateById(Teacher teacher);

    /**
     * 删除教师（逻辑删除）
     *
     * @param id 教师ID
     */
    void deleteById(Integer id);
}