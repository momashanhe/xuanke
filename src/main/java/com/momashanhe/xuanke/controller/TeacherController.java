package com.momashanhe.xuanke.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/view/teacher")
public class TeacherController {
    /**
     * 教师首页
     *
     * @return 教师首页视图
     */
    @RequestMapping("/home")
    public String home() {
        return "teacher/home";
    }
}
