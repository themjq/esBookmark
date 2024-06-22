package com.example.service;

import com.example.pojo.BookMark;
import com.example.pojo.PageBean;
import com.example.pojo.User;

import java.time.LocalDate;
import java.util.List;

/**
 * 书签管理
 */
public interface BookmarkService {
    /**
     * 分页查询
     * @param page
     * @param pageSize
     * @return
     */
    PageBean page(Integer page, Integer pageSize,Integer userId,String title, Short status,Short typeId,LocalDate begin,LocalDate end);

    /**
     * 分页查询2 用户端
     * @param page
     * @param pageSize
     * @return
     */
    PageBean page2(Integer page, Integer pageSize,Integer userId,String title, Short status,Short typeId,LocalDate begin,LocalDate end);

    /**
     * 批量删除
     * @param ids
     */
    void delete(List<Integer> ids);

    /**
     * 新增
     */
    void save(BookMark bookMark);

    /**
     * 根据ID查询
     * @param id
     * @return
     */
    BookMark getById(Integer id);

    /**
     * 更新
     */
    void update(BookMark bookMark);

}
