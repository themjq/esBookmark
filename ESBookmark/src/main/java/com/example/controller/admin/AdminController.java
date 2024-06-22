package com.example.controller.admin;

import com.example.pojo.Administrator;
import com.example.pojo.PageBean;
import com.example.pojo.Result;
import com.example.pojo.User;
import com.example.service.AdminService;
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
@RequestMapping("/admin/admin")
public class AdminController {

    @Autowired
    private AdminService adminService;

    @GetMapping
    public Result page(@RequestParam(defaultValue = "1") Integer page,
                       @RequestParam(defaultValue = "10") Integer pageSize,
                       String name, Short sex,
                       @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate begin,
                       @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate end){
        log.info("分页查询, 参数: {},{},{},{},{},{}",page,pageSize,name,sex,begin,end);
        //调用service分页查询
        PageBean pageBean = adminService.page(page,pageSize,name,sex,begin,end);
        return Result.success(pageBean);
    }

    @DeleteMapping("/{ids}")
    public Result delete(@PathVariable List<Integer> ids){
        log.info("批量删除操作, ids:{}",ids);
        adminService.delete(ids);
        return Result.success();
    }

    @PostMapping
    public Result save(@RequestBody Administrator administrator){
        log.info("新增管理员: {}", administrator);
        if(administrator.getPassword()==""||administrator.getPassword()==null)
            administrator.setPassword("123456");
        if(administrator.getStatus()==null)
            administrator.setStatus((short)0); //修改为0 正常 1禁用
        adminService.save(administrator);
        return Result.success();
    }

    @GetMapping("/{id}")
    public Result getById(@PathVariable Integer id){
        log.info("根据ID查询管理员信息, id: {}",id);
        User user = adminService.getById(id);
        return Result.success(user);
    }

    @PutMapping
    public Result update(@RequestBody Administrator administrator){
        log.info("更新管理员信息 : {}", administrator);
        adminService.update(administrator);
        return Result.success();
    }
}





