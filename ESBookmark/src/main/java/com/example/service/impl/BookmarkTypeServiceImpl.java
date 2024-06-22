package com.example.service.impl;

import com.example.mapper.BookmarkMapper;
import com.example.mapper.BookmarkTypeMapper;
import com.example.pojo.BookMark;
import com.example.pojo.BookMarkType;
import com.example.service.BookmarkTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class BookmarkTypeServiceImpl implements BookmarkTypeService {

    @Autowired
    private BookmarkTypeMapper bookMarkTypeMapper;
    @Autowired
    private BookmarkMapper bookmarkMapper;

    @Override
    public List<BookMarkType> list() {
        return bookMarkTypeMapper.list();
    }

    @Override
    public void delete(Integer id) {
        // 根据id修改要删除的书签类型对应的书签
        List<BookMark> bookMarkList=bookmarkMapper.getListById(id);
        for(BookMark bookMark :bookMarkList){
            bookMark.setTypeId(1);  //1代表未分类， 把删除的书签类型对应的书签类型置为未分类
            bookmarkMapper.update(bookMark);
        }
        bookMarkTypeMapper.deleteById(id);
    }

    @Override
    public void add(BookMarkType bookMarkType) {
        bookMarkType.setCreateTime(LocalDateTime.now());
        bookMarkType.setUpdateTime(LocalDateTime.now());
        bookMarkTypeMapper.insert(bookMarkType);
    }

    @Override
    public BookMarkType getById(Integer id) {
        return bookMarkTypeMapper.getById(id);
    }

    @Override
    public void update(BookMarkType bookMarkType) {
        bookMarkTypeMapper.update(bookMarkType);
    }
}
