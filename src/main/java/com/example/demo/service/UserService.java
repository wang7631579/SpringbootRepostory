package com.example.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.entity.User;
import com.example.demo.mapper.UserMapper;

@Service
public class UserService {
	
	@Autowired
	UserMapper userMapper;
	
	
	
	public void insertUser() {		
		User user = new User();
		user.setUserName("3");
		user.setUserAge("3");
		userMapper.insertUser(user);
		user.setUserName("4");
		user.setUserName("4");
		userMapper.insertUser(user);
		throw new RuntimeException();
		
	}
}
