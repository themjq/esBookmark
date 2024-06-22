package com.example.service.impl;

import com.example.dto.BookMarkDto;
import com.example.mapper.BookmarkMapper;
import com.example.mapper.BookmarkTypeMapper;
import com.example.mapper.UserMapper;
import com.example.pojo.BookMarkType;
import com.example.pojo.PageBean;
import com.example.pojo.BookMark;
import com.example.service.BookmarkService;
import com.example.service.UserService;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
public class BookmarkServiceImpl implements BookmarkService {

    @Autowired
    private BookmarkMapper bookmarkMapper;
    @Autowired
    private UserMapper userMapper;
    @Autowired
    private BookmarkTypeMapper bookmarkTypeMapper;
    @Override
    public PageBean page(Integer page, Integer pageSize,Integer userId,String title, Short status,Short typeId, LocalDate begin, LocalDate end) {
        //1. 设置分页参数
        PageHelper.startPage(page,pageSize);

        //2. 执行查询
        List<BookMark> bookMarkList = bookmarkMapper.list(userId,title,status,typeId, begin, end);

        //这里需要加个判断，判断属性是否为空  但是不符合前端设计 故注释
//        List<BookMarkDto> bookMarkDtoList=new ArrayList<>();
//        for(BookMark bookMark :bookMarkList){
//            BookMarkDto bookMarkDto=new BookMarkDto();
//            BeanUtils.copyProperties(bookMark,bookMarkDto);  //拷贝相同名称的属性
//            bookMarkDto.setUsername(userMapper.getById(bookMark.getId()).getUsername());
//            bookMarkDto.setType(bookmarkTypeMapper.getById(bookMark.getTypeId()).getType());
//            bookMarkDtoList.add(bookMarkDto);
//        }
//        Page<BookMarkDto> p = (Page<BookMarkDto>) bookMarkDtoList;

        Page<BookMark> p = (Page<BookMark>) bookMarkList;
        //3. 封装PageBean对象
        PageBean pageBean = new PageBean(p.getTotal(), p.getResult());
        return pageBean;
    }

    @Override
    public void delete(List<Integer> ids) {
        bookmarkMapper.delete(ids);
    }

    @Override
    public void save(BookMark bookMark) {
        bookMark.setCreateTime(LocalDateTime.now());
        bookMark.setUpdateTime(LocalDateTime.now());
        bookmarkMapper.insert(bookMark);
    }

    @Override
    public BookMark getById(Integer id) {
        return bookmarkMapper.getById(id);
    }

    @Override
    public void update(BookMark bookMark) {
        bookMark.setUpdateTime(LocalDateTime.now());

        bookmarkMapper.update(bookMark);
    }


}
