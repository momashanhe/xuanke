package com.momashanhe.xuanke.business;

import com.momashanhe.xuanke.entity.Course;
import com.momashanhe.xuanke.entity.Teacher;
import com.momashanhe.xuanke.service.CourseService;
import com.momashanhe.xuanke.service.TeacherService;
import com.momashanhe.xuanke.util.BusinessUtil;
import com.momashanhe.xuanke.util.BusinessUtil.Result;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
@RequestMapping("/api/teacher")
public class TeacherBusiness {
    @Resource
    private TeacherService teacherService;
    @Resource
    private CourseService courseService;

    /**
     * 教师登录
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
            Teacher teacher = teacherService.login(username, password);
            if (teacher != null) {
                session.setAttribute("user", teacher);
                session.setAttribute("role", "teacher");
                return BusinessUtil.success();
            }
            return BusinessUtil.error("用户名或密码错误");
        } catch (Exception e) {
            e.printStackTrace();
            return BusinessUtil.error("登录失败");
        }
    }

    /**
     * 获取教师详情
     *
     * @param session HttpSession对象
     * @return 教师信息
     */
    @RequestMapping("/detail")
    @ResponseBody
    public Result<Teacher> detail(HttpSession session) {
        try {
            Teacher teacher = (Teacher) session.getAttribute("user");
            return BusinessUtil.success(teacherService.findById(teacher.getId()));
        } catch (Exception e) {
            e.printStackTrace();
            return BusinessUtil.error("获取失败");
        }
    }

    /**
     * 更新教师信息
     *
     * @param session HttpSession对象
     * @param teacher 教师信息
     * @return 更新结果
     */
    @RequestMapping("/update")
    @ResponseBody
    public Result<Void> update(HttpSession session, Teacher teacher) {
        try {
            Teacher current = (Teacher) session.getAttribute("user");
            teacher.setId(current.getId());
            teacherService.updateById(teacher);
            return BusinessUtil.success();
        } catch (Exception e) {
            e.printStackTrace();
            return BusinessUtil.error("更新失败");
        }
    }

    /**
     * 获取教师的课程列表
     *
     * @param session HttpSession对象
     * @return 课程列表
     */
    @RequestMapping("/listCourseWithTeacherId")
    @ResponseBody
    public Result<List<Course>> listCourseWithTeacherId(HttpSession session) {
        try {
            Teacher teacher = (Teacher) session.getAttribute("user");
            return BusinessUtil.success(courseService.listByTeacherId(teacher.getId()));
        } catch (Exception e) {
            e.printStackTrace();
            return BusinessUtil.error("获取失败");
        }
    }

    /**
     * 获取课程详情
     *
     * @param id 课程ID
     * @return 课程信息
     */
    @RequestMapping("/detailCourse")
    @ResponseBody
    public Result<Course> detailCourse(Integer id) {
        try {
            return BusinessUtil.success(courseService.findById(id));
        } catch (Exception e) {
            e.printStackTrace();
            return BusinessUtil.error("获取失败");
        }
    }

    /**
     * 添加课程
     *
     * @param session HttpSession对象
     * @param course 课程信息
     * @return 添加结果
     */
    @RequestMapping("/addCourse")
    @ResponseBody
    public Result<Void> addCourse(HttpSession session, Course course) {
        try {
            Teacher teacher = (Teacher) session.getAttribute("user");
            course.setTeacherId(teacher.getId());
            courseService.add(course);
            return BusinessUtil.success();
        } catch (Exception e) {
            e.printStackTrace();
            return BusinessUtil.error("添加失败");
        }
    }

    /**
     * 更新课程
     *
     * @param course 课程信息
     * @return 更新结果
     */
    @RequestMapping("/updateCourse")
    @ResponseBody
    public Result<Void> updateCourse(Course course) {
        try {
            courseService.updateById(course);
            return BusinessUtil.success();
        } catch (Exception e) {
            e.printStackTrace();
            return BusinessUtil.error("更新失败");
        }
    }

    /**
     * 删除课程
     *
     * @param id 课程ID
     * @return 删除结果
     */
    @RequestMapping("/deleteCourse")
    @ResponseBody
    public Result<Void> deleteCourse(Integer id) {
        try {
            courseService.deleteById(id);
            return BusinessUtil.success();
        } catch (Exception e) {
            e.printStackTrace();
            return BusinessUtil.error("删除失败");
        }
    }
}