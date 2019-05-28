package com.book.dao;

import java.io.Serializable;
import java.util.Set;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class RedisDao {

    private RedisTemplate<Serializable, Object> redisTemplate;

    @Autowired
    public void setRedisTemplate(
            RedisTemplate<Serializable, Object> redisTemplate) {
        this.redisTemplate = redisTemplate;
    }

    //往有序集合中添加新的value对象
    public boolean addSortedSet(final String key, Object name,double count) {
        boolean result = false;
        try {
            //opsForValue表明是对SortedList类型的容器进行操作
            redisTemplate.opsForZSet().add(key, name, count);
            result = true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    //对有序集合中已存在的某个value对象进行销量的增加
    public boolean editSortedSet(final String key, Object name, double count){
        boolean result = false;
        try {
            //opsForValue表明是对SortedList类型的容器进行操作
            redisTemplate.opsForZSet().incrementScore(key, name, count);
            result = true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    //获取有序集合的前几名对象
    public Set getSortedSet(final String key, long start, long end) {
        Set result = redisTemplate.opsForZSet().range(key, start, end);
        return result;
    }

    //获取key队列中某一个field的位置
    public Long getPosition(final String key,String field){ return redisTemplate.opsForZSet().rank(key, field); }

}