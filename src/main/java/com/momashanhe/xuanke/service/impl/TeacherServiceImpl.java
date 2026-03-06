package com.momashanhe.xuanke.service.impl;

import com.momashanhe.xuanke.entity.Course;
import com.momashanhe.xuanke.entity.Teacher;
import com.momashanhe.xuanke.mapper.CourseMapper;
import com.momashanhe.xuanke.mapper.SelectionMapper;
import com.momashanhe.xuanke.mapper.TeacherMapper;
import com.momashanhe.xuanke.service.TeacherService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class TeacherServiceImpl implements TeacherService {
    @Resource
    private TeacherMapper teacherMapper;
    @Resource
    private CourseMapper courseMapper;
    @Resource
    private SelectionMapper selectionMapper;

    /**
     * 教师登录
     *
     * @param username 用户名
     * @param password 密码
     * @return 教师对象，登录失败返回null
     */
    @Override
    public Teacher login(String username, String password) {
        Teacher teacher = teacherMapper.findByUsername(username);
        if (teacher != null && teacher.getPassword().equals(encrypt(password))) {
            return teacher;
        }
        return null;
    }

    /**
     * 根据ID查询教师
     *
     * @param id 教师ID
     * @return 教师对象
     */
    @Override
    public Teacher findById(Integer id) {
        return teacherMapper.findById(id);
    }

    /**
     * 获取所有教师列表
     *
     * @return 教师列表
     */
    @Override
    public List<Teacher> list() {
        return teacherMapper.list();
    }

    /**
     * 添加教师
     *
     * @param teacher 教师信息
     */
    @Override
    @Transactional
    public void add(Teacher teacher) {
        teacher.setPassword(encrypt(teacher.getPassword()));
        teacherMapper.insert(teacher);
    }

    /**
     * 更新教师
     *
     * @param teacher 教师信息
     */
    @Override
    @Transactional
    public void updateById(Teacher teacher) {
        if (teacher.getPassword() != null && !teacher.getPassword().isEmpty()) {
            teacher.setPassword(encrypt(teacher.getPassword()));
        }
        teacherMapper.updateById(teacher);
    }

    /**
     * 删除教师（逻辑删除）
     *
     * @param id 教师ID
     */
    @Override
    @Transactional
    public void deleteById(Integer id) {
        // 先删除选课记录
        List<Course> courseList = courseMapper.listByTeacherId(id);
        selectionMapper.deleteByCourseIdList(courseList.stream().map(Course::getId).collect(Collectors.toList()));
        // 再删除课程（逻辑删除）
        courseMapper.deleteByTeacherId(id);
        // 再删除教师（逻辑删除）
        teacherMapper.deleteById(id);
    }

    /**
     * 密码加密
     *
     * @param password 原始密码
     * @return 加密后的密码
     */
    private String encrypt(String password) {
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            md.update(password.getBytes());
            byte[] bytes = md.digest();
            StringBuilder sb = new StringBuilder();
            for (byte b : bytes) {
                sb.append(String.format("%02x", b));
            }
            return sb.toString();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
            return password;
        }
    }
}