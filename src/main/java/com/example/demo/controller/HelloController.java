package com.example.demo.controller;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.example.demo.mapper.UserMapper;
import com.example.demo.service.UserService;

@Controller
public class HelloController {
	
	@Autowired
	DataSource dataSource;
	
	@Autowired
	UserMapper userMapper;

	@Autowired
	UserService userService;
	
	
	@GetMapping("/hello")
	@ResponseBody
	public String helloWorld(String userName) {
		System.out.println(dataSource);
		System.out.println("userName ================ " + userName);		
		return "helloWorld";
	}
	
	
	@GetMapping("/testCacheable")
	@ResponseBody
	@Cacheable(value="users", key="#root.methodName+'['+#userName+']'")
	public String testCacheable(String userName) {
		System.out.println(userName);
		userMapper.getUserByName("name1"); 
		return userMapper.getUserByName("name1").getUserAge();
	}
	
	@GetMapping("/testCachePut")
	@ResponseBody
	@CachePut(value="users", key="#root.methodName+'['+#userName+']'")
	public String testCachePut(String userName) {
		System.out.println(userName);
		userMapper.getUserByName("name1"); 
		return userMapper.getUserByName("name1").getUserAge();
	}
	
	@GetMapping("/testCacheEvict")
	@ResponseBody
	@CacheEvict(value="users", key="#root.methodName+'['+#userName+']'")
	public String testCacheEvict(String userName) {
		System.out.println(userName);
		userMapper.getUserByName("name1"); 
		return userMapper.getUserByName("name1").getUserAge();
	}
	
	
	
	@GetMapping("/insertUser")
	@ResponseBody
	public String insertUser(String userName) {
		userService.insertUser();
		return "helloWorld";
	}
	
	
	
	
	/*@Autowired
    private RedisTemplate<String,Object> redisTemplate;
	
	@GetMapping("/hello2")
	@ResponseBody
	public String hello2() {
		ValueOperations<String,Object> valueOperations = redisTemplate.opsForValue();
        valueOperations.set("hello", "redis");
        System.out.println("useRedisDao = " + valueOperations.get("hello"));
		return "2132132";

	}*/
	
}
