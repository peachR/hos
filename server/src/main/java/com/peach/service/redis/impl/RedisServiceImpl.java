package com.peach.service.redis.impl;

import com.peach.service.redis.RedisService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.ListOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Service;

import java.io.Serializable;
import java.util.*;
import java.util.concurrent.TimeUnit;

@Service
public class RedisServiceImpl implements RedisService {
    @Autowired
    private RedisTemplate redisTemplate;

    public void remove(String... keys) {
        for(String key : keys){
            remove(key);
        }
    }

    public void removePattern(String pattern) {
        Set<Serializable> keys = redisTemplate.keys(pattern);
        if(keys.size() > 0){
            redisTemplate.delete(keys);
        }
    }

    public void remove(String key) {
        if(exist(key)){
            redisTemplate.delete(key);
        }
    }

    public boolean exist(final String key){
        return redisTemplate.hasKey(key);
    }

    public Serializable get(String key) {
        Serializable result = null;
        ValueOperations<Serializable,Serializable> operations = redisTemplate.opsForValue();
        result = operations.get(key);
        return result;
    }

    public boolean set(String key, Serializable value) {
        boolean result = false;
        try{
            ValueOperations<Serializable,Serializable> operations = redisTemplate.opsForValue();
            operations.set(key, value);
            result = true;
        }catch (Exception ex){
            ex.printStackTrace();
        }
        return result;
    }

    public boolean set(String key, Serializable value, long expireTime) {
        boolean result = false;
        try{
            ValueOperations<Serializable, Serializable> operations = redisTemplate.opsForValue();
            operations.set(key,value);
            redisTemplate.expire(key, expireTime, TimeUnit.SECONDS);
            result = true;
        }catch (Exception ex){
            ex.printStackTrace();
        }
        return result;
    }

    public Serializable getSet(final String key, Serializable value){
        Serializable result = null;
        try{
            ValueOperations<Serializable, Serializable> operations = redisTemplate.opsForValue();
            result = operations.getAndSet(key, value);
        }catch (Exception ex){
            ex.printStackTrace();
        }
        return result;
    }

    public boolean setIfAbsent(final String key, Serializable value){
        boolean result =false;
        try{
            ValueOperations<Serializable, Serializable> operations = redisTemplate.opsForValue();
            result = operations.setIfAbsent(key, value);
        }catch (Exception ex){
            remove(key);
            result = false;
            ex.printStackTrace();

        }
        return result;
    }

    public <K, HK, HV> boolean hmset(K key, Map<HK, HV> map, Long expireTime) {
        HashOperations<K,HK,HV> operations = redisTemplate.opsForHash();
        operations.putAll(key, map);

        if(expireTime != null){
            redisTemplate.expire(key, expireTime, TimeUnit.SECONDS);
        }
        return true;
    }

    public <K,HK,HV> boolean hmset(K key, Map<HK, HV> map){
        return hmset(key, map, null);
    }

    public <K, HK, HV> Map<HK, HV> hgetAll(K key) {
        HashOperations<K, HK, HV> operations = redisTemplate.opsForHash();
        return operations.entries(key);
    }

    public <K, LV> boolean leftPushAll(K key, Long expireTime, LV ...values) {
        ListOperations<K, LV> operations = redisTemplate.opsForList();
        operations.leftPushAll(key,values);
        if(expireTime != null){
            redisTemplate.expire(key, expireTime, TimeUnit.SECONDS);
        }
        return true;
    }

    public <K, LV> boolean leftPushAll(K key, Collection<LV> values, Long expireTime) {
        ListOperations<K, LV> operations = redisTemplate.opsForList();

        operations.leftPushAll(key, values);

        if(expireTime != null){
            redisTemplate.expire(key, expireTime, TimeUnit.SECONDS);
        }
        return true;
    }

    public <K, LV> boolean leftPushAll(K key, Collection<LV> values){
        return leftPushAll(key, values, null);
    }

    public <K, LV> List<LV> lRange(K key, long fromIndex, long toIndex){
        ListOperations<K, LV> operations = redisTemplate.opsForList();

        return operations.range(key, fromIndex, toIndex);
    }

    public <K, LV> List<LV> lGetAll(K key){
        return lRange(key, 0, -1);
    }

    public <K, LV> List<LV> getAllListByPrefix(K prefix){
        Set<K> keys = redisTemplate.keys(prefix);
        List<LV> result = new ArrayList<>(keys.size());
        for(K key : keys){
            result.addAll(lGetAll(key));
        }
        return result;
    }

    public void watch(String key){
        try{
            redisTemplate.watch(key);
        }catch (Exception ex){
        }
    }

    public void watch(Collection<String> keys){
        try{
            redisTemplate.watch(keys);
        }catch (Exception ex){
        }
    }

    public void unwatch(){
        try{
            redisTemplate.unwatch();
        }catch (Exception ex){
        }
    }

    public void setExpire(String key, long expireTime){
        redisTemplate.expire(key, expireTime, TimeUnit.MILLISECONDS);
    }

    public long getExpire(String key){
        return redisTemplate.getExpire(key);
    }

}
