package com.example.controller.user;

import com.example.pojo.Result;
import com.example.pojo.User;
import com.example.service.UserService;
import com.example.utils.JwtUtils;
import com.example.utils.ThreadLocalData;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

@Slf4j
@RestController
@RequestMapping("/user")
public class UserLoginController {

    @Autowired
    private UserService userService;

    @PostMapping("/login")
    public Result login(@RequestBody User user1){
        log.info("用户登录: {}", user1);
        User user = userService.login(user1);

        //登录成功,生成令牌,下发令牌
        if (user != null){
            Map<String, Object> claims = new HashMap<>();
            claims.put("id", user.getId());
            claims.put("name", user.getName());
            claims.put("username", user.getUsername());

            String jwt = JwtUtils.generateJwt(claims); //jwt包含了当前登录的员工信息
            return Result.success(jwt);
        }

        //登录失败, 返回错误信息
        return Result.error("用户名或密码错误");
    }

    @PostMapping("/register")
    public Result register(@RequestBody User user){
        log.info("用户注册: {}", user);
        if(user.getStatus()==null)
            user.setStatus((short)0); //修改为0 正常 1禁用
        if(user.getCreateTime()==null)
            user.setCreateTime(LocalDateTime.now());
        if(user.getUpdateTime()==null)
            user.setUpdateTime(LocalDateTime.now());
         boolean flag=userService.register(user);
         if(flag)
             return Result.success();
        //登录失败, 返回错误信息
        return Result.error("用户名已存在");
    }
}
