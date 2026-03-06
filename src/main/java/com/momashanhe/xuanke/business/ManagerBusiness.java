package com.momashanhe.xuanke.business;

import com.momashanhe.xuanke.entity.Manager;
import com.momashanhe.xuanke.entity.Student;
import com.momashanhe.xuanke.entity.Teacher;
import com.momashanhe.xuanke.service.ManagerService;
import com.momashanhe.xuanke.service.StudentService;
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
@RequestMapping("/api/manager")
public class ManagerBusiness {
    @Resource
    private ManagerService managerService;
    @Resource
    private StudentService studentService;
    @Resource
    private TeacherService teacherService;

    /**
     * 管理员登录
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
            Manager manager = managerService.login(username, password);
            if (manager != null) {
                session.setAttribute("user", manager);
                session.setAttribute("role", "manager");
                return BusinessUtil.success();
            }
            return BusinessUtil.error("用户名或密码错误");
        } catch (Exception e) {
            e.printStackTrace();
            return BusinessUtil.error("登录失败");
        }
    }

    /**
     * 获取管理员详情
     *
     * @param session HttpSession对象
     * @return 管理员信息
     */
    @RequestMapping("/detail")
    @ResponseBody
    public Result<Manager> detail(HttpSession session) {
        try {
            Manager manager = (Manager) session.getAttribute("user");
            return BusinessUtil.success(managerService.findById(manager.getId()));
        } catch (Exception e) {
            e.printStackTrace();
            return BusinessUtil.error("获取失败");
        }
    }

    /**
     * 更新管理员信息
     *
     * @param session HttpSession对象
     * @param manager 管理员信息
     * @return 更新结果
     */
    @RequestMapping("/update")
    @ResponseBody
    public Result<Void> update(HttpSession session, Manager manager) {
        try {
            Manager current = (Manager) session.getAttribute("user");
            manager.setId(current.getId());
            managerService.updateById(manager);
            return BusinessUtil.success();
        } catch (Exception e) {
            e.printStackTrace();
            return BusinessUtil.error("更新失败");
        }
    }

    /**
     * 获取学生列表
     *
     * @return 学生列表
     */
    @RequestMapping("/listStudent")
    @ResponseBody
    public Result<List<Student>> listStudent() {
        try {
            return BusinessUtil.success(studentService.list());
        } catch (Exception e) {
            e.printStackTrace();
            return BusinessUtil.error("获取失败");
        }
    }

    /**
     * 添加学生
     *
     * @param student 学生信息
     * @return 添加结果
     */
    @RequestMapping("/addStudent")
    @ResponseBody
    public Result<Void> addStudent(Student student) {
        try {
            studentService.add(student);
            return BusinessUtil.success();
        } catch (Exception e) {
            e.printStackTrace();
            return BusinessUtil.error("添加失败");
        }
    }

    /**
     * 删除学生
     *
     * @param id 学生ID
     * @return 删除结果
     */
    @RequestMapping("/deleteStudent")
    @ResponseBody
    public Result<Void> deleteStudent(Integer id) {
        try {
            studentService.deleteById(id);
            return BusinessUtil.success();
        } catch (Exception e) {
            e.printStackTrace();
            return BusinessUtil.error("删除失败");
        }
    }

    /**
     * 获取教师列表
     *
     * @return 教师列表
     */
    @RequestMapping("/listTeacher")
    @ResponseBody
    public Result<List<Teacher>> listTeacher() {
        try {
            return BusinessUtil.success(teacherService.list());
        } catch (Exception e) {
            e.printStackTrace();
            return BusinessUtil.error("获取失败");
        }
    }

    /**
     * 添加教师
     *
     * @param teacher 教师信息
     * @return 添加结果
     */
    @RequestMapping("/addTeacher")
    @ResponseBody
    public Result<Void> addTeacher(Teacher teacher) {
        try {
            teacherService.add(teacher);
            return BusinessUtil.success();
        } catch (Exception e) {
            e.printStackTrace();
            return BusinessUtil.error("添加失败");
        }
    }

    /**
     * 删除教师
     *
     * @param id 教师ID
     * @return 删除结果
     */
    @RequestMapping("/deleteTeacher")
    @ResponseBody
    public Result<Void> deleteTeacher(Integer id) {
        try {
            teacherService.deleteById(id);
            return BusinessUtil.success();
        } catch (Exception e) {
            e.printStackTrace();
            return BusinessUtil.error("删除失败");
        }
    }
}