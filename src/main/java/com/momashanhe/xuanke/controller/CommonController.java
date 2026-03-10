package com.momashanhe.xuanke.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;

@Controller
@RequestMapping("/view/common")
public class CommonController {
    /**
     * 退出登录
     *
     * @param session HttpSession对象
     * @return 重定向到登录页面
     */
    @RequestMapping("/logout")
    public String logout(HttpSession session) {
        session.invalidate();
        return "redirect:/";
    }
}
