package com.example.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.GenericJackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;

@Configuration
public class RedisConfig {
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
     *在不使用序列化配置之前Java数据序列化底层使用的是默认序列化存入Redis中是解析过乱码的数据，存到Redis中可视化太低！使用之后可以显示中文
     * @param lettuceConnectionFactory
     * @return
     */

    @Bean
    public RedisTemplate<String,Object> redisTemplate(LettuceConnectionFactory lettuceConnectionFactory){
        RedisTemplate<String, Object> redisTemplate = new RedisTemplate<>();
        redisTemplate.setConnectionFactory(lettuceConnectionFactory);
        //设置key序列化方式String
        redisTemplate.setKeySerializer(new StringRedisSerializer());
        //设置value的序列化方式json，使用GenericJackson2JsonRedisSerializer替换默认的序列化
        redisTemplate.setValueSerializer(new GenericJackson2JsonRedisSerializer());
        redisTemplate.setHashKeySerializer(new StringRedisSerializer());
        redisTemplate.setHashValueSerializer(new GenericJackson2JsonRedisSerializer());
        redisTemplate.afterPropertiesSet();
        return redisTemplate;
    }
}