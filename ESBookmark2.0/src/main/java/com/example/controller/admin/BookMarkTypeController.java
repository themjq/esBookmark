package com.example.controller.admin;

import com.example.pojo.BookMarkType;
import com.example.pojo.Result;
import com.example.service.BookmarkTypeService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 书签类型管理Controller
 */
@Slf4j
@RequestMapping("/admin/bm_type")
@RestController
public class BookMarkTypeController {

    //private static Logger log = LoggerFactory.getLogger(DeptController.class);
    @Autowired
    private BookmarkTypeService bookMarkTypeService;

    /**
     * 查询书签类型数据
     * @return
     */
    //@RequestMapping(method = RequestMethod.GET) //指定请求方式为GET
    @GetMapping
    public Result list(){
        log.info("查询全部类型数据");
        List<BookMarkType> bookMarkList =  bookMarkTypeService.list();
        return Result.success(bookMarkList);
    }
    @GetMapping("/{id}")
    public Result getById(@PathVariable Integer id){
        log.info("根据id查询书签类型:{}",id);
        BookMarkType bookMarkType =  bookMarkTypeService.getById(id);
        return Result.success(bookMarkType);
    }

    /**
     * 删除书签类型
     * @return
     */
    @DeleteMapping("/{id}")
    public Result delete(@PathVariable Integer id){
        log.info("根据id删除类型:{}",id);
        bookMarkTypeService.delete(id);
        return Result.success();
    }
    /**
     * 更新类型
     * @return
     */
    @PutMapping
    public Result update(@RequestBody BookMarkType bookMarkType){
        log.info("更新类型: {}" , bookMarkType);
        bookMarkTypeService.update(bookMarkType);
        return Result.success();
    }
    /**
     * 新增类型
     * @return
     */
    @PostMapping
    public Result add(@RequestBody BookMarkType bookMarkType){
        log.info("新增类型: {}" , bookMarkType);
        bookMarkTypeService.add(bookMarkType);
        return Result.success();
    }
}
