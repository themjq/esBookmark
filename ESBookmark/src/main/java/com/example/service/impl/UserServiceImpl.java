package com.example.service.impl;

import com.example.pojo.Administrator;
import com.example.pojo.User;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.example.mapper.UserMapper;
import com.example.pojo.PageBean;
import com.example.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;


    @Override
    public PageBean page(Integer page, Integer pageSize,String name, Short sex, LocalDate begin, LocalDate end) {
        //1. 设置分页参数
        PageHelper.startPage(page,pageSize);

        //2. 执行查询
        List<User> userList = userMapper.list(name, sex, begin, end);
        Page<User> p = (Page<User>) userList;

        //3. 封装PageBean对象
        PageBean pageBean = new PageBean(p.getTotal(), p.getResult());
        return pageBean;
    }

    @Override
    public void delete(List<Integer> ids) {
        userMapper.delete(ids);
    }

    @Override
    public void save(User user) {
        user.setCreateTime(LocalDateTime.now());
        user.setUpdateTime(LocalDateTime.now());
        userMapper.insert(user);
    }

    @Override
    public User getById(Integer id) {
        return userMapper.getById(id);
    }

    @Override
    public void update(User user) {
        user.setUpdateTime(LocalDateTime.now());

        userMapper.update(user);
    }

    @Override
    public User login(User user) {
        return userMapper.getByUsernameAndPassword(user);
    }

    /**
     * 注册
     */
    @Override
    public boolean register(User user) {

        try{
            User db=userMapper.getByUserName(user);
            if(db==null){
                userMapper.insert(user);
                return true;
            }
        }catch (Exception e){
            return false;
        }
        return false;
    }
}
