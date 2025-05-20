package com.example.bhd.service.impl;

import com.example.bhd.service.RedisService;
import lombok.AllArgsConstructor;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.concurrent.TimeUnit;

@Service
@AllArgsConstructor
public class RedisServiceImpl implements RedisService {
    private RedisTemplate<String, Object> redisTemplate;

    @Override
    public void saveValue(String key, Object value) {
        int TIME_EXPIRATION = 7;
        redisTemplate.opsForValue().set(key, value, TIME_EXPIRATION, TimeUnit.MINUTES);
    }

    @Override
    public Object getValue(String key) {
        return redisTemplate.opsForValue().get(key);
    }

    @Override
    public void delete(String key) {
        redisTemplate.delete(key);
    }
}
