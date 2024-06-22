package com.example;

import com.alibaba.fastjson.JSON;
import com.example.enums.KeyRedis;

import org.assertj.core.groups.Tuple;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ZSetOperations;
import org.springframework.data.redis.core.ZSetOperations.TypedTuple;
import java.util.*;
import java.util.concurrent.TimeUnit;

@SpringBootTest
public class RedisTest {
 
    /**
     * redis序列化的工具配置类，下面这个请一定开启配置
     * 127.0.0.1:6379 keys *
     * 1)"ord:102" 序列化过
     * 2)"\xac\xed\x00\x05t\ord:102"  没有序列化过
     * this.redisTemplate.opsForValue()//提供了操作String类型的所有方法
     * this.redisTemplate.opsForList()//提供了操作List类型的所有方法
     * this.redisTemplate.opsForSet()//提供了操作Set类型的所有方法
     * this.redisTemplate.opsForHash()//提供了操作Hash类型的所有方法
     * this.redisTemplate.opsForZSet()//提供了操作ZSet类型的所有方法
     *
     *
     * @param lettuceConnectionFactory
     * @return
     */
 
    @Autowired
    RedisTemplate redisTemplate;
 
    @Test
    public void test1(){
 
            //boundhashOps也是操作redis的一种方法，但是是低版本操作。
            //操作方式和opsForhash一样。根据个人喜好都可以
        Object content = redisTemplate.boundHashOps("content").get("1");
        System.out.println(content);
    }
 
        /**
            存储string类型
    */
    @Test
    public void test2(){
        /**
         *  redisTemplate.opsForValue()//提供了操作String类型的所有方法
         */
            Object name ="dession";
 
            redisTemplate.opsForValue().set("name",name);
            //设置过期时间1天过期
            redisTemplate.expire("name",1, TimeUnit.DAYS);
 
        System.out.println(redisTemplate.opsForValue().get("name"));
        //  结果为：dession
 
 
 
    }
 
        /**
                存储Hash数据类型
            */
    @Test
    public void test3(){
        //单个存储
        redisTemplate.opsForHash().put("users","name","zhangsan");
        //设置过期时间
        redisTemplate.expire("users",1, TimeUnit.DAYS);
        //多只存储
        Map map = new HashMap();
        map.put("age",12);
        map.put("sex","男");
        redisTemplate.opsForHash().putAll("users",map);
 
        Object o = redisTemplate.opsForHash().get("users", "name");
        System.out.println(o);
    }
    @Test
    public void test4(){
        Integer bookmarkId=1;
        Integer like=1;
        Double score=1.0;
        if(redisTemplate.opsForZSet().rank(KeyRedis.RANK_KEY.getKey(),bookmarkId)==null){
            redisTemplate.opsForZSet().add(KeyRedis.RANK_KEY.getKey(),bookmarkId,KeyRedis.UserScore.getScore());
        }else redisTemplate.opsForZSet().incrementScore(KeyRedis.RANK_KEY.getKey(),bookmarkId,like==KeyRedis.LIKE.getIslike()?score:(0-score));
    }
    @Test
    public void test5(){
        Integer t=1;
        if(redisTemplate.opsForHash().hasKey(KeyRedis.LIKE_KEY.getKey(),t.toString())){

        }else redisTemplate.opsForHash().put(KeyRedis.LIKE_KEY.getKey(),"1",1);
    }

    @Test
    public void test6() {
        ZSetOperations<String, Object> zSetOperations = redisTemplate.opsForZSet();
        Set<ZSetOperations.TypedTuple<Object>> zsetKey = zSetOperations.reverseRangeWithScores(KeyRedis.RANK_KEY.getKey(), 0, 9);
        Map<Integer,Integer> map=new LinkedHashMap<>();
        for (ZSetOperations.TypedTuple<Object> key:zsetKey){
            map.put(Integer.parseInt(key.getValue().toString()),key.getScore().intValue());
        }

        for(Map.Entry<Integer,Integer> m:map.entrySet()){
            System.out.println(m.getKey()+" "+m.getValue());
        }
    }
    @Test
    public void test7(){
        Integer bookmarkId=26;
        Integer userId=12;
        //redisTemplate.opsForSet().add(KeyRedis.LIKE_KEY.getKey()+bookmarkId,userId);
        //redisTemplate.opsForSet().add(KeyRedis.DISLIKE_KEY.getKey()+bookmarkId,userId);
//        redisTemplate.opsForSet().add(KeyRedis.LIKE_KEY.getKey()+bookmarkId,userId);
//        //redisTemplate.opsForSet().add(KeyRedis.DISLIKE_KEY.getKey()+bookmarkId,userId);
//        System.out.println(redisTemplate.opsForSet().isMember(KeyRedis.DISLIKE_KEY.getKey()+bookmarkId,userId));
//        System.out.println(redisTemplate.opsForSet().isMember(KeyRedis.DISLIKE_KEY.getKey()+bookmarkId,2));
//
//        System.out.println(redisTemplate.opsForSet().remove(KeyRedis.LIKE_KEY.getKey()+bookmarkId,userId));
        System.out.println(        redisTemplate.opsForSet().add(KeyRedis.LIKE_KEY.getKey()+bookmarkId,userId));
    }
}