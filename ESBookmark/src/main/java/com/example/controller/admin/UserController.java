package com.example.controller.admin;

import com.example.pojo.User;
import com.example.pojo.PageBean;
import com.example.pojo.Result;
import com.example.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;


import java.time.LocalDate;
import java.util.List;

/**
 * 员工管理Controller
 */
@Slf4j
@RestController
@RequestMapping("/admin/user")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping
    public Result page(@RequestParam(defaultValue = "1") Integer page,
                       @RequestParam(defaultValue = "10") Integer pageSize,
                       String name, Short sex,
                       @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate begin,
                       @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate end){
        log.info("分页查询, 参数: {},{},{},{},{},{}",page,pageSize,name,sex,begin,end);
        //调用service分页查询
        PageBean pageBean = userService.page(page,pageSize,name,sex,begin,end);
        return Result.success(pageBean);
    }

    @DeleteMapping("/{ids}")
    public Result delete(@PathVariable List<Integer> ids){
        log.info("批量删除操作, ids:{}",ids);
        userService.delete(ids);
        return Result.success();
    }

    @PostMapping
    public Result save(@RequestBody User user){
        log.info("新增用户: {}", user);
        if(user.getPassword()==""||user.getPassword()==null)
            user.setPassword("123456");
        userService.save(user);
        return Result.success();
    }

    @GetMapping("/{id}")
    public Result getById(@PathVariable Integer id){
        log.info("根据ID查询用户信息, id: {}",id);
        User user = userService.getById(id);
        return Result.success(user);
    }

    @PutMapping
    public Result update(@RequestBody User user){
        log.info("更新用户信息 : {}", user);
        userService.update(user);
        return Result.success();
    }
}





