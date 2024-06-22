package com.example.service;

import com.example.pojo.BookMarkType;

import java.util.List;

/**
 * 部门管理
 */
public interface BookmarkTypeService {
    /**
     * 查询全部部门数据
     * @return
     */
    List<BookMarkType> list();

    /**
     * 删除书签类型
     * @param id
     */
    void delete(Integer id);

    /**
     * 新增书签类型
     * @param bookMark
     */
    void add(BookMarkType bookMarkTyoe);
    /**
     * 根据id查询
     */
    BookMarkType getById(Integer id);

    /**
     *更新类型
     */
    void update(BookMarkType bookMarkType);
}
