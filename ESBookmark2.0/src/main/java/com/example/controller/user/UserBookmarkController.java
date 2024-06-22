package com.example.controller.user;

import com.example.pojo.BookMark;
import com.example.pojo.PageBean;
import com.example.pojo.Result;
import com.example.service.BookmarkService;
import com.example.utils.ThreadLocalData;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

/**
 * 书签管理Controller
 */
@Slf4j
@RestController
@RequestMapping("/user/bookmark")
public class UserBookmarkController {

    @Autowired
    private BookmarkService bookmarkService;

    @GetMapping
    public Result page(@RequestParam(defaultValue = "1") Integer page,
                       @RequestParam(defaultValue = "10") Integer pageSize,
                       String title, Short status,Short typeId,
                       @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate begin,
                       @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate end){
        log.info("分页查询, 参数: {},{},{},{},{},{}",page,pageSize,title,status,typeId,begin,end);
        //调用service分页查询
        PageBean pageBean = bookmarkService.page2(page,pageSize,null,title,status,typeId,begin,end);
        return Result.success(pageBean);
    }
    @GetMapping("/my")
    public Result page2(@RequestParam(defaultValue = "1") Integer page,
                       @RequestParam(defaultValue = "10") Integer pageSize,
                       String title, Short status,Short typeId,
                       @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate begin,
                       @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate end){
        log.info("分页查询, 参数: {},{},{},{},{},{},{}",page,pageSize,ThreadLocalData.getCurrentId(),title,status,typeId,begin,end);

        //调用service分页查询
        PageBean pageBean = bookmarkService.page(page,pageSize,ThreadLocalData.getCurrentId(),title,status,typeId,begin,end);
        return Result.success(pageBean);
    }
    @DeleteMapping("/{ids}")
    public Result delete(@PathVariable List<Integer> ids){
        log.info("批量删除操作, ids:{}",ids);
        bookmarkService.delete(ids);
        return Result.success();
    }

    @PostMapping
    public Result save(@RequestBody BookMark bookMark){
        log.info("新增书签: {}", bookMark);
        //bookMark.setStatus((short) 0);  //默认初始化为0 私密
        bookMark.setUserId(ThreadLocalData.getCurrentId());
        bookmarkService.save(bookMark);
        return Result.success();
    }

    @GetMapping("/{id}")
    public Result getById(@PathVariable Integer id){
        log.info("根据ID查询书签信息, id: {}",id);
        BookMark bookMark = bookmarkService.getById(id);
        return Result.success(bookMark);
    }

    @PutMapping
    public Result update(@RequestBody BookMark bookMark){
        log.info("更新书签信息 : {}", bookMark);
        bookmarkService.update(bookMark);
        return Result.success();
    }
}





