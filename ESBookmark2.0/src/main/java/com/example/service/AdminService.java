package com.example.service;

import com.example.pojo.Administrator;
import com.example.pojo.PageBean;
import com.example.pojo.User;

import java.time.LocalDate;
import java.util.List;

/**
 * 管理员用户管理
 */
public interface AdminService {
    /**
     * 分页查询
     * @param page
     * @param pageSize
     * @return
     */
    PageBean page(Integer page, Integer pageSize,String name, Short sex,LocalDate begin,LocalDate end);

    /**
     * 批量删除
     * @param ids
     */
    void delete(List<Integer> ids);

    /**
     * 新增管理员
     */
    void save(Administrator administrator);

    /**
     * 根据ID查询管理员
     * @param id
     * @return
     */
    User getById(Integer id);

    /**
     * 更新管理员
     */
    void update(Administrator administrator);

    /**
     * 管理员登录
     * @return
     */
    Administrator login(Administrator administrator);
}
