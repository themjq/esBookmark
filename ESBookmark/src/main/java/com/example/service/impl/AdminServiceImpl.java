package com.example.service.impl;

import com.example.mapper.AdminMapper;
import com.example.mapper.UserMapper;
import com.example.pojo.Administrator;
import com.example.pojo.PageBean;
import com.example.pojo.User;
import com.example.service.AdminService;
import com.example.service.UserService;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class AdminServiceImpl implements AdminService {

    @Autowired
    private AdminMapper adminMapper;
    @Override
    public PageBean page(Integer page, Integer pageSize,String name, Short sex, LocalDate begin, LocalDate end) {
        //1. 设置分页参数
        PageHelper.startPage(page,pageSize);

        //2. 执行查询
        List<Administrator> adminiList = adminMapper.list(name, sex, begin, end);
        Page<Administrator> admin = (Page<Administrator>) adminiList;

        //3. 封装PageBean对象
        PageBean pageBean = new PageBean(admin.getTotal(), admin.getResult());
        return pageBean;
    }

    @Override
    public void delete(List<Integer> ids) {
        adminMapper.delete(ids);
    }

    @Override
    public void save(Administrator administrator) {
        administrator.setCreateTime(LocalDateTime.now());
        administrator.setUpdateTime(LocalDateTime.now());
        adminMapper.insert(administrator);
    }

    @Override
    public User getById(Integer id) {
        return adminMapper.getById(id);
    }

    @Override
    public void update(Administrator administrator) {
        administrator.setUpdateTime(LocalDateTime.now());

        adminMapper.update(administrator);
    }

    @Override
    public Administrator login(Administrator administrator) {
        return adminMapper.getByUsernameAndPassword(administrator);
    }

}
