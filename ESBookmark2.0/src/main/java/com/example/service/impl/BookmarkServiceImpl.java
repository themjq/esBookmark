package com.example.service.impl;

import com.example.dto.BookMarkDto;
import com.example.mapper.BookmarkMapper;
import com.example.mapper.BookmarkTypeMapper;
import com.example.mapper.UserMapper;
import com.example.pojo.BookMarkType;
import com.example.pojo.PageBean;
import com.example.pojo.BookMark;
import com.example.pojo.User;
import com.example.service.BookmarkService;
import com.example.service.UserService;
import com.example.utils.RedisUtil;
import com.example.utils.ThreadLocalData;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

@Service
@Slf4j
public class BookmarkServiceImpl implements BookmarkService {

    @Autowired
    private BookmarkMapper bookmarkMapper;
    @Autowired
    private UserMapper userMapper;
    @Autowired
    private BookmarkTypeMapper bookmarkTypeMapper;
    @Autowired
    private RedisUtil redisUtil;
    @Override
    public PageBean page(Integer page, Integer pageSize,Integer userId,String title, Short status,Short typeId, LocalDate begin, LocalDate end) {
        //1. 设置分页参数
        PageHelper.startPage(page,pageSize);

        //2. 执行查询
        List<BookMark> bookMarkList = bookmarkMapper.list(userId,title,status,typeId, begin, end);
        for (BookMark bookMark:bookMarkList){
            boolean like=redisUtil.isLike(bookMark.getId(),ThreadLocalData.getCurrentId());
            boolean disLike=redisUtil.isDisLike(bookMark.getId(),ThreadLocalData.getCurrentId());
            log.info("判断用户:{} 对于书签:{} 点赞:{} 不喜欢:{}", ThreadLocalData.getCurrentId(),bookMark.getId(),like,disLike);

        }
        Page<BookMark> p = (Page<BookMark>) bookMarkList;
        //3. 封装PageBean对象
        PageBean pageBean = new PageBean(p.getTotal(), p.getResult());
        return pageBean;
    }

    /**
     * 用户端 分页查询书签
     */
    @Override
    public PageBean page2(Integer page, Integer pageSize,Integer userId,String title, Short status,Short typeId, LocalDate begin, LocalDate end) {
        //1. 设置分页参数
        PageHelper.startPage(page,pageSize);

        //2. 执行查询
        List<BookMark> bookMarkList = bookmarkMapper.list(userId,title,status,typeId, begin, end);

        //这里需要加个判断，判断属性是否为空
        List<BookMarkDto> bookMarkDtoList=new ArrayList<>();
        for(BookMark bookMark :bookMarkList){
            BookMarkDto bookMarkDto=new BookMarkDto();
            BeanUtils.copyProperties(bookMark,bookMarkDto);  //拷贝相同名称的属性
            User user=userMapper.getById(bookMark.getId());
            String username=bookMark.getUserId().toString();
            if(user!=null&&user.getUsername()!=null)
                username=user.getUsername();
            bookMarkDto.setUsername(username);
            //bookMarkDto.setType(bookmarkTypeMapper.getById(bookMark.getTypeId()).getType());   //因前端设计中调用了相应的获取类型的方法，故此处注释

            boolean like=redisUtil.isLike(bookMark.getId(),ThreadLocalData.getCurrentId());
            boolean disLike=redisUtil.isDisLike(bookMark.getId(),ThreadLocalData.getCurrentId());
            if(like &&!disLike){
                bookMarkDto.setIsLike(1);
            }else if(!like && disLike){
                bookMarkDto.setIsLike(-1);
            }else{
                bookMarkDto.setIsLike(0);
            }
            log.info("判断用户:{} 对于书签:{} 点赞:{} 不喜欢:{}", ThreadLocalData.getCurrentId(),bookMark.getId(),like,disLike);
            bookMarkDtoList.add(bookMarkDto);
        }
//        Page<BookMarkDto> p = (Page<BookMarkDto>) bookMarkDtoList;  //这个会报错，无法类型转换
        /**
         * PageHelper.startPage(pageNum,pageSize) 只对其后的第一次SQL查询进行分页
         * 原因：其实查出来的那个ist是带分页效果的，但是new的对象他仅仅是一个lis对象，不能强转成Page，所以会报
         * 转换异常错误。
         * 所以需要使用PageInfo获取分页信息 然后转换
         */
        PageInfo<BookMarkDto> pageInfo=new PageInfo<>(bookMarkDtoList);
        //3. 封装PageBean对象
        PageBean pageBean = new PageBean(pageInfo.getTotal(), pageInfo.getList());
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
