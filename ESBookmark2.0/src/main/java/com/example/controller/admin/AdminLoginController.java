package com.example.controller.admin;

import com.example.pojo.Administrator;
import com.example.pojo.Result;
import com.example.service.AdminService;
import com.example.utils.JwtUtils;
import com.example.utils.ThreadLocalData;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@Slf4j
@RestController
@RequestMapping("/admin")
public class AdminLoginController {

    @Autowired
    private AdminService adminService;

    @PostMapping("/login")
    public Result login(@RequestBody Administrator administrator){
        log.info("管理员登录: {}", administrator);
        Administrator admin = adminService.login(administrator);

        //登录成功,生成令牌,下发令牌
        if (admin != null){
            Map<String, Object> claims = new HashMap<>();
            claims.put("id", admin.getId());
            claims.put("name", admin.getName());
            claims.put("username", admin.getUsername());
            ThreadLocalData.setCurrentId(administrator.getId());
            String jwt = JwtUtils.generateJwt(claims); //jwt包含了当前登录的员工信息
            return Result.success(jwt);
        }

        //登录失败, 返回错误信息
        return Result.error("用户名或密码错误");
    }

}
