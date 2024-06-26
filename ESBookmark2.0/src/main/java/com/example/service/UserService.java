package com.example.service;

import com.example.pojo.User;
import com.example.pojo.PageBean;

import java.time.LocalDate;
import java.util.List;

/**
 * 员工管理
 */
public interface UserService {
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
     * 新增员工
     * @param user
     */
    void save(User user);

    /**
     * 根据ID查询员工
     * @param id
     * @return
     */
    User getById(Integer id);

    /**
     * 更新员工
     * @param user
     */
    void update(User user);

    /**
     * 员工登录
     * @param user
     * @return
     */
    User login(User user);

    /**
     *注册
     */
    boolean register(User user);
}
