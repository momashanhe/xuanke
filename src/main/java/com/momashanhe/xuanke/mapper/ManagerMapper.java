package com.momashanhe.xuanke.mapper;

import com.momashanhe.xuanke.entity.Manager;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface ManagerMapper {
    /**
     * 根据用户名查询管理员
     *
     * @param username 用户名
     * @return 管理员对象
     */
    Manager findByUsername(@Param("username") String username);

    /**
     * 根据ID查询管理员
     *
     * @param id 管理员ID
     * @return 管理员对象
     */
    Manager findById(@Param("id") Integer id);

    /**
     * 根据ID更新管理员
     *
     * @param manager 管理员信息
     */
    void updateById(Manager manager);
}