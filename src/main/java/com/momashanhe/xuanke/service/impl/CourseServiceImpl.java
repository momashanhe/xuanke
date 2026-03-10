package com.momashanhe.xuanke.service.impl;

import com.momashanhe.xuanke.entity.Course;
import com.momashanhe.xuanke.mapper.CourseMapper;
import com.momashanhe.xuanke.mapper.SelectionMapper;
import com.momashanhe.xuanke.service.CourseService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

@Service
public class CourseServiceImpl implements CourseService {
    @Resource
    private CourseMapper courseMapper;
    @Resource
    private SelectionMapper selectionMapper;

    /**
     * 根据ID查询课程
     *
     * @param id 课程ID
     * @return 课程对象
     */
    @Override
    public Course findById(Integer id) {
        Course course = courseMapper.findById(id);
        if (course != null) {
            course.setCurrentCount(selectionMapper.countByCourseId(id));
        }
        return course;
    }

    /**
     * 获取所有课程列表
     *
     * @return 课程列表
     */
    @Override
    public List<Course> list() {
        List<Course> courses = courseMapper.list();
        for (Course course : courses) {
            course.setCurrentCount(selectionMapper.countByCourseId(course.getId()));
        }
        return courses;
    }

    /**
     * 根据教师ID查询课程列表
     *
     * @param teacherId 教师ID
     * @return 课程列表
     */
    @Override
    public List<Course> listByTeacherId(Integer teacherId) {
        List<Course> courses = courseMapper.listByTeacherId(teacherId);
        for (Course course : courses) {
            course.setCurrentCount(selectionMapper.countByCourseId(course.getId()));
        }
        return courses;
    }

    /**
     * 添加课程
     *
     * @param course 课程信息
     */
    @Override
    @Transactional
    public void add(Course course) {
        courseMapper.insert(course);
    }

    /**
     * 更新课程
     *
     * @param course 课程信息
     */
    @Override
    @Transactional
    public void updateById(Course course) {
        courseMapper.updateById(course);
    }

    /**
     * 删除课程（逻辑删除）
     *
     * @param id 课程ID
     */
    @Override
    @Transactional
    public void deleteById(Integer id) {
        // 先删除选课记录
        selectionMapper.deleteByCourseId(id);
        // 再删除课程（逻辑删除）
        courseMapper.deleteById(id);
    }
}