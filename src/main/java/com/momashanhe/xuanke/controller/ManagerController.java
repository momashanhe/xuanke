package com.momashanhe.xuanke.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/view/manager")
public class ManagerController {
    /**
     * 管理员首页
     *
     * @return 管理员首页视图
     */
    @RequestMapping("/home")
    public String home() {
        return "manager/home";
    }
}