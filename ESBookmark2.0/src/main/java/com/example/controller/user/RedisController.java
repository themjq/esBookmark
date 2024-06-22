package com.example.controller.user;

import com.example.dto.RankDto;
import com.example.enums.KeyRedis;
import com.example.mapper.BookmarkMapper;
import com.example.mapper.BookmarkTypeMapper;
import com.example.mapper.UserMapper;
import com.example.pojo.BookMark;
import com.example.pojo.Result;
import com.example.pojo.User;
import com.example.service.BookmarkService;
import com.example.service.BookmarkTypeService;
import com.example.service.UserService;
import com.example.utils.RedisUtil;
import com.example.utils.ThreadLocalData;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Slf4j
@RestController
@RequestMapping("/redis")
public class RedisController {

    @Autowired
    private RedisUtil redisUtil;

    @Autowired
    private UserMapper userMapper;
    @Autowired
    private BookmarkMapper bookmarkMapper;
    @Autowired
    private BookmarkTypeMapper bookmarkTypeMapper;

    /**
     * 用户点赞
     */
    @PostMapping("/like")
    public Result like(Integer bookmarkId){
        log.info("用户:{} 点赞了书签:{}", ThreadLocalData.getCurrentId(),bookmarkId);
        if(redisUtil.setRedisLikeList(bookmarkId,ThreadLocalData.getCurrentId())){
            redisUtil.setRedisRank(bookmarkId,KeyRedis.UserScore.getScore(), KeyRedis.LIKE.getIslike());
            return Result.success();
        }

        return Result.error("点赞失败");
    }

    /**
     * 用户取消点赞
     */
    @PostMapping("/deleteLike")
    public Result deleteLike(Integer bookmarkId){
        log.info("用户:{} 取消点赞书签:{}", ThreadLocalData.getCurrentId(),bookmarkId);
        if(redisUtil.deleteLike(bookmarkId,ThreadLocalData.getCurrentId())){
            redisUtil.setRedisRank(bookmarkId,KeyRedis.UserScore.getScore(), KeyRedis.DISLIKE.getIslike());
            return Result.success();
        }

        return Result.error("取消点赞失败");
    }


    /**
     * 用户不喜欢
     */
    @PostMapping("/disLike")
    public Result dislike(Integer bookmarkId){
        log.info("用户:{} 不喜欢了书签:{}",ThreadLocalData.getCurrentId(),bookmarkId);
        if(redisUtil.setRedisDisLikeList(bookmarkId,ThreadLocalData.getCurrentId())){
            redisUtil.setRedisRank(bookmarkId,KeyRedis.UserScore.getScore(), KeyRedis.DISLIKE.getIslike());
            return Result.success();
        }
        return Result.error("不喜欢失败");
    }

    /**
     * 用户取消不喜欢
     */
    @PostMapping("/deleteDisLike")
    public Result deleteDislike(Integer bookmarkId){
        log.info("用户:{} 取消不喜欢书签:{}",ThreadLocalData.getCurrentId(),bookmarkId);
        if(redisUtil.deleteDisLike(bookmarkId,ThreadLocalData.getCurrentId())){
            redisUtil.setRedisRank(bookmarkId,KeyRedis.UserScore.getScore(), KeyRedis.LIKE.getIslike());
            return Result.success();
        }
        return Result.error("取消不喜欢失败");
    }
    /**
     * 获取排行榜数据
     * 此处未分service层
     */
    @PostMapping("/getRedisRank")
    public Result getRedisRank(){
        log.info("用户:{} 查看排行榜",ThreadLocalData.getCurrentId());

        Map<Integer,Integer> bmap=redisUtil.getRedisRank(); //从redis中获取排行榜数据 包含 书签id和分数
        List<RankDto> list=new ArrayList<>();  //存放返回前端的数据
        for(Map.Entry<Integer, Integer> map:bmap.entrySet()){
            RankDto rankDto=new RankDto(); //创建变量，赋值后存储到list中
            //key为书签id  value为分数

            BookMark bookMark=bookmarkMapper.getById(map.getKey());
            rankDto.setTitle(bookMark.getTitle());
            rankDto.setUrl(bookMark.getUrl());
            rankDto.setImage(bookMark.getImage());
            rankDto.setType(bookmarkTypeMapper.getById(bookMark.getTypeId()).getType());
            rankDto.setId(bookMark.getId());

            User user=userMapper.getById(bookMark.getUserId());
            if(user!=null){
                String username=user.getUsername();
                if(user!=null&&user.getUsername()!=null)
                    username=user.getUsername();

                rankDto.setUsername(username);
            }else rankDto.setUsername("用户不存在");


            list.add(rankDto);
        }
        if(bmap!=null)
            return Result.success(list);
        return Result.error("排行榜无数据");
    }
}
