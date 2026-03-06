package com.momashanhe.xuanke.business;

import com.momashanhe.xuanke.entity.Course;
import com.momashanhe.xuanke.entity.Selection;
import com.momashanhe.xuanke.entity.Student;
import com.momashanhe.xuanke.service.CourseService;
import com.momashanhe.xuanke.service.SelectionService;
import com.momashanhe.xuanke.service.StudentService;
import com.momashanhe.xuanke.util.BusinessUtil;
import com.momashanhe.xuanke.util.BusinessUtil.Result;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
@RequestMapping("/api/student")
public class StudentBusiness {
    @Resource
    private StudentService studentService;
    @Resource
    private CourseService courseService;
    @Resource
    private SelectionService selectionService;

    /**
     * 学生登录
     *
     * @param session HttpSession对象
     * @param username 用户名
     * @param password 密码
     * @return 登录结果
     */
    @RequestMapping("/login")
    @ResponseBody
    public Result<Void> login(HttpSession session, String username, String password) {
        try {
            Student student = studentService.login(username, password);
            if (student != null) {
                session.setAttribute("user", student);
                session.setAttribute("role", "student");
                return BusinessUtil.success();
            }
            return BusinessUtil.error("用户名或密码错误");
        } catch (Exception e) {
            e.printStackTrace();
            return BusinessUtil.error("登录失败");
        }
    }

    /**
     * 获取学生详情
     *
     * @param session HttpSession对象
     * @return 学生信息
     */
    @RequestMapping("/detail")
    @ResponseBody
    public Result<Student> detail(HttpSession session) {
        try {
            Student student = (Student) session.getAttribute("user");
            return BusinessUtil.success(studentService.findById(student.getId()));
        } catch (Exception e) {
            e.printStackTrace();
            return BusinessUtil.error("获取失败");
        }
    }

    /**
     * 更新学生信息
     *
     * @param session HttpSession对象
     * @param student 学生信息
     * @return 更新结果
     */
    @RequestMapping("/update")
    @ResponseBody
    public Result<Void> update(HttpSession session, Student student) {
        try {
            Student current = (Student) session.getAttribute("user");
            student.setId(current.getId());
            studentService.updateById(student);
            return BusinessUtil.success();
        } catch (Exception e) {
            e.printStackTrace();
            return BusinessUtil.error("更新失败");
        }
    }

    /**
     * 获取课程列表
     *
     * @return 课程列表
     */
    @RequestMapping("/listCourse")
    @ResponseBody
    public Result<List<Course>> listCourse() {
        try {
            return BusinessUtil.success(courseService.list());
        } catch (Exception e) {
            e.printStackTrace();
            return BusinessUtil.error("获取失败");
        }
    }

    /**
     * 获取已选课程列表
     *
     * @param session HttpSession对象
     * @return 已选课程列表
     */
    @RequestMapping("/listSelectionByStudentId")
    @ResponseBody
    public Result<List<Selection>> listSelectionByStudentId(HttpSession session) {
        try {
            Student student = (Student) session.getAttribute("user");
            return BusinessUtil.success(selectionService.listByStudentId(student.getId()));
        } catch (Exception e) {
            e.printStackTrace();
            return BusinessUtil.error("获取失败");
        }
    }

    /**
     * 选课
     *
     * @param session HttpSession对象
     * @param courseId 课程ID
     * @return 选课结果
     */
    @RequestMapping("/addCourse")
    @ResponseBody
    public Result<Void> addCourse(HttpSession session, Integer courseId) {
        try {
            Student student = (Student) session.getAttribute("user");
            selectionService.add(student.getId(), courseId);
            return BusinessUtil.success();
        } catch (Exception e) {
            return BusinessUtil.error(e.getMessage());
        }
    }

    /**
     * 退课
     *
     * @param id 选课记录ID
     * @return 退课结果
     */
    @RequestMapping("/deleteCourse")
    @ResponseBody
    public Result<Void> deleteCourse(Integer id) {
        try {
            selectionService.deleteById(id);
            return BusinessUtil.success();
        } catch (Exception e) {
            e.printStackTrace();
            return BusinessUtil.error("退课失败");
        }
    }
}