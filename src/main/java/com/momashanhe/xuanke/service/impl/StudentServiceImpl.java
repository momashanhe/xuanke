package com.momashanhe.xuanke.service.impl;

import com.momashanhe.xuanke.entity.Student;
import com.momashanhe.xuanke.mapper.SelectionMapper;
import com.momashanhe.xuanke.mapper.StudentMapper;
import com.momashanhe.xuanke.service.StudentService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.List;

@Service
public class StudentServiceImpl implements StudentService {
    @Resource
    private StudentMapper studentMapper;
    @Resource
    private SelectionMapper selectionMapper;

    /**
     * 学生登录
     *
     * @param username 用户名
     * @param password 密码
     * @return 学生对象，登录失败返回null
     */
    @Override
    public Student login(String username, String password) {
        Student student = studentMapper.findByUsername(username);
        if (student != null && student.getPassword().equals(encrypt(password))) {
            return student;
        }
        return null;
    }

    /**
     * 根据ID查询学生
     *
     * @param id 学生ID
     * @return 学生对象
     */
    @Override
    public Student findById(Integer id) {
        return studentMapper.findById(id);
    }

    /**
     * 获取所有学生列表
     *
     * @return 学生列表
     */
    @Override
    public List<Student> list() {
        return studentMapper.list();
    }

    /**
     * 添加学生
     *
     * @param student 学生信息
     */
    @Override
    @Transactional
    public void add(Student student) {
        student.setPassword(encrypt(student.getPassword()));
        studentMapper.insert(student);
    }

    /**
     * 更新学生
     *
     * @param student 学生信息
     */
    @Override
    @Transactional
    public void updateById(Student student) {
        if (student.getPassword() != null && !student.getPassword().isEmpty()) {
            student.setPassword(encrypt(student.getPassword()));
        }
        studentMapper.updateById(student);
    }

    /**
     * 删除学生（逻辑删除）
     *
     * @param id 学生ID
     */
    @Override
    @Transactional
    public void deleteById(Integer id) {
        // 先删除选课记录
        selectionMapper.deleteByStudentId(id);
        // 再删除学生（逻辑删除）
        studentMapper.deleteById(id);
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