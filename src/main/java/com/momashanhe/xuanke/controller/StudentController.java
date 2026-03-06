package com.momashanhe.xuanke.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/view/student")
public class StudentController {
    /**
     * 学生首页
     *
     * @return 学生首页视图
     */
    @RequestMapping("/home")
    public String home() {
        return "student/home";
    }
}
