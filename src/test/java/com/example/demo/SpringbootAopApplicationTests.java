package com.example.demo;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.example.demo.entity.Teacher;
import com.example.demo.mapper.StudentMapper;
import com.example.demo.mapper.UserMapper;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SpringbootAopApplicationTests {

	@Autowired UserMapper userMapper;
	
	
	@Autowired StudentMapper studentMapper;
	
	@Test
	public void contextLoads() {
		//System.out.println(userMapper.getUserByName("name1").getUserAge());
	}

	@Test
	public void test1() {
		//System.out.println(userMapper.getUserByName("name1").getUserAge());
		
		//User user = new User();
		//user.setUserName("213213212132");
		//user.setUserName(null);
		//userMapper.insertUser(user);
		
		//User user = new User();
		//user.setUserName("213213212132");
		//user.setUserAge("111");
		//userMapper.updateUser(user);
		
		
		/*User user = new User();
		user.setUserAge("111");
		userMapper.deleteUser(user);*/
		
		
		/*Map<String,Object> aa = new HashMap<>();
		aa.put("age", "111");
		List<Map> bb=studentMapper.getTeachers1(aa);
		System.out.println(bb);
		System.out.println(bb);*/
		
		/*List<Map> bb=studentMapper.getTeachers2("111");
		System.out.println(bb);*/
		
		try {
			Map<String,Object> aa = new HashMap<>();
			aa.put("age", "111");
			List<Map> bb=studentMapper.getTeachers3(aa);
			System.out.println(bb);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	
	
}
