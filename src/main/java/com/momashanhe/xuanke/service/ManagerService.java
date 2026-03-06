package com.momashanhe.xuanke.service;

import com.momashanhe.xuanke.entity.Manager;

public interface ManagerService {
    /**
     * 管理员登录
     *
     * @param username 用户名
     * @param password 密码
     * @return 管理员对象，登录失败返回null
     */
    Manager login(String username, String password);

    /**
     * 根据ID查询管理员
     *
     * @param id 管理员ID
     * @return 管理员对象
     */
    Manager findById(Integer id);

    /**
     * 更新管理员
     *
     * @param manager 管理员信息
     */
    void updateById(Manager manager);
}