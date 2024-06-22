package com.example.utils;

import com.alibaba.fastjson.JSON;
import com.example.enums.KeyRedis;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.core.*;
import org.springframework.stereotype.Component;


import java.util.*;
 
@Component
public class RedisUtil {
 
    @Autowired
    private RedisTemplate redisTemplate;
    /**
     * 存入数据--排行榜
     * 使用ZSET实现，书签ID作VALUE  当用户点赞或不喜欢书签时,SCORE存方的分值根据用户分值 增加或降低
     */
    public void setRedisRank(Integer bookmarkId,Double score,Integer like){
        score=like==KeyRedis.LIKE.getIslike()?score:(0-score);  //根据当前类型给score赋值
        //判断是否存在数据，不存在则创建
        if(redisTemplate.opsForZSet().rank(KeyRedis.RANK_KEY.getKey(),bookmarkId)==null){
            redisTemplate.opsForZSet().add(KeyRedis.RANK_KEY.getKey(),bookmarkId,score);
        }else redisTemplate.opsForZSet().incrementScore(KeyRedis.RANK_KEY.getKey(),bookmarkId,score);
    }
    /**
     * 存入数据--点赞列表
     * 使用Set实现，书签ID作二层Key 用户ID作Value
     */
    public boolean setRedisLikeList(Integer bookmarkId,Integer userId){
        return redisTemplate.opsForSet().add(KeyRedis.LIKE_KEY.getKey() + bookmarkId, userId) == 1;
    }
    /**
     * 存入数据--不喜欢列表
     * 使用Set实现，书签ID作二层Key 用户ID作Value
     */
    public boolean setRedisDisLikeList(Integer bookmarkId,Integer userId){
        return redisTemplate.opsForSet().add(KeyRedis.DISLIKE_KEY.getKey()+bookmarkId,userId)==1;
    }

    /**
     * 获取数据--排行榜
     */
    public Map<Integer,Integer> getRedisRank(){  //前十数据
        ZSetOperations<String, Object> zSetOperations = redisTemplate.opsForZSet();
        Set<ZSetOperations.TypedTuple<Object>> zsetKey = zSetOperations.reverseRangeWithScores(KeyRedis.RANK_KEY.getKey(), 0, 9);

        Map<Integer,Integer> map=new LinkedHashMap<>();
        for (ZSetOperations.TypedTuple<Object> key:zsetKey){
//            System.out.println(key.getValue()+" "+key.getScore());
            map.put(Integer.parseInt(key.getValue().toString()),key.getScore().intValue());
        }
        return map;
    }

    /**
     * 获取数据--根据书签ID 和 用户ID 判断是否点赞
     */
    public boolean isLike(Integer bookmarkId,Integer userId){
        return redisTemplate.opsForSet().isMember(KeyRedis.LIKE_KEY.getKey()+bookmarkId,userId);
    }

    /**
     * 获取数据--根据书签ID 和 用户ID 判断是否不喜欢
     */
    public boolean isDisLike(Integer bookmarkId,Integer userId){
        return redisTemplate.opsForSet().isMember(KeyRedis.DISLIKE_KEY.getKey()+bookmarkId,userId);
    }

    /**
     * 删除数据--根据书签ID 和 用户ID 判断是否点赞
     */
    public boolean deleteLike(Integer bookmarkId,Integer userId){
       if(redisTemplate.opsForSet().isMember(KeyRedis.LIKE_KEY.getKey()+bookmarkId,userId)){
          if(redisTemplate.opsForSet().remove(KeyRedis.LIKE_KEY.getKey()+bookmarkId,userId)==1)
              return true;
            return false;
       }
       return false;
    }

    /**
     * 删除数据--根据书签ID 和 用户ID 判断是否不喜欢
     */
    public boolean deleteDisLike(Integer bookmarkId,Integer userId){
        if(redisTemplate.opsForSet().isMember(KeyRedis.DISLIKE_KEY.getKey()+bookmarkId,userId)){
            if(redisTemplate.opsForSet().remove(KeyRedis.DISLIKE_KEY.getKey()+bookmarkId,userId)==1)
                return true;
            return false;
        }
        return false;
    }
}