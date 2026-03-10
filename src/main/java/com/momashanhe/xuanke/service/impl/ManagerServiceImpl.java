package com.momashanhe.xuanke.service.impl;

import com.momashanhe.xuanke.entity.Manager;
import com.momashanhe.xuanke.mapper.ManagerMapper;
import com.momashanhe.xuanke.service.ManagerService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

@Service
public class ManagerServiceImpl implements ManagerService {
    @Resource
    private ManagerMapper managerMapper;

    /**
     * 管理员登录
     *
     * @param username 用户名
     * @param password 密码
     * @return 管理员对象，登录失败返回null
     */
    @Override
    public Manager login(String username, String password) {
        Manager manager = managerMapper.findByUsername(username);
        if (manager != null && manager.getPassword().equals(encrypt(password))) {
            return manager;
        }
        return null;
    }

    /**
     * 根据ID查询管理员
     *
     * @param id 管理员ID
     * @return 管理员对象
     */
    @Override
    public Manager findById(Integer id) {
        return managerMapper.findById(id);
    }

    /**
     * 更新管理员
     *
     * @param manager 管理员信息
     */
    @Override
    @Transactional
    public void updateById(Manager manager) {
        if (manager.getPassword() != null && !manager.getPassword().isEmpty()) {
            manager.setPassword(encrypt(manager.getPassword()));
        }
        managerMapper.updateById(manager);
    }

    /**
     * 密码加密
     *
     * @param password 原始密码
     * @return 加密后的密码
     */
    private String encrypt(String password) {
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            md.update(password.getBytes());
            byte[] bytes = md.digest();
            StringBuilder sb = new StringBuilder();
            for (byte b : bytes) {
                sb.append(String.format("%02x", b));
            }
            return sb.toString();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
            return password;
        }
    }
}