package com.migu.online.service;

import java.util.concurrent.TimeUnit;

import javax.annotation.Resource;

import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

@Service
public class RedisService {

	@Resource
	private StringRedisTemplate stringRedisTemplate;
	@Resource
	private RedisTemplate<String, Object> redisTemplate;

	public void set(String key, Object obj) {
		redisTemplate.opsForValue().set(key, obj);
	}

	public void set(String key, Object obj, int seconds) {
		redisTemplate.opsForValue().set(key, obj, seconds, TimeUnit.SECONDS);
	}
		
	public Object get(String key) {
		return redisTemplate.boundValueOps(key).get();
	}
	
	public Long getExpire(String key) {
		return redisTemplate.boundValueOps(key).getExpire();
	}

	public void setString(String key, String code) {
		stringRedisTemplate.opsForValue().set(key, code);
	}

	public void setString(String key, String code, int seconds) {
		stringRedisTemplate.opsForValue().set(key, code, seconds, TimeUnit.SECONDS);
	}
	
	public String getString(String key) {
		return stringRedisTemplate.boundValueOps(key).get();
	}
	
	public Long getStringExpire(String key) {
		return stringRedisTemplate.boundValueOps(key).getExpire();
	}
	
	public void del(String key) {
		stringRedisTemplate.delete(key);
	}
	
	public void delObjct(String key) {
		redisTemplate.delete(key);
	}
	

}
