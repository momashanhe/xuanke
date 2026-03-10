package com.momashanhe.xuanke.service.impl;

import com.momashanhe.xuanke.entity.Selection;
import com.momashanhe.xuanke.mapper.CourseMapper;
import com.momashanhe.xuanke.mapper.SelectionMapper;
import com.momashanhe.xuanke.service.SelectionService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

@Service
public class SelectionServiceImpl implements SelectionService {
    @Resource
    private SelectionMapper selectionMapper;
    @Resource
    private CourseMapper courseMapper;

    /**
     * 根据学生ID查询选课记录
     *
     * @param studentId 学生ID
     * @return 选课记录列表
     */
    @Override
    public List<Selection> listByStudentId(Integer studentId) {
        return selectionMapper.listByStudentId(studentId);
    }

    /**
     * 学生选课
     *
     * @param studentId 学生ID
     * @param courseId 课程ID
     * @throws RuntimeException 选课失败时抛出异常
     */
    @Override
    @Transactional
    public void add(Integer studentId, Integer courseId) {
        // 检查是否已经选过该课程
        Integer count = selectionMapper.countByStudentIdAndCourseId(studentId, courseId);
        if (count > 0) {
            throw new RuntimeException("已经选过该课程");
        }
        // 检查课程容量
        Integer currentCount = selectionMapper.countByCourseId(courseId);
        Integer capacity = courseMapper.findById(courseId).getCapacity();
        if (currentCount >= capacity) {
            throw new RuntimeException("课程容量已满");
        }
        // 执行选课
        Selection selection = new Selection();
        selection.setStudentId(studentId);
        selection.setCourseId(courseId);
        selectionMapper.insert(selection);
    }

    /**
     * 学生退课
     *
     * @param id 选课记录ID
     */
    @Override
    @Transactional
    public void deleteById(Integer id) {
        selectionMapper.deleteById(id);
    }
}