package com.example.bhd.service;

public interface RedisService {

    void saveValue(String key, Object value);

    Object getValue(String key);

    void delete(String key);

}
