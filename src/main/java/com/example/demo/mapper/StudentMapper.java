package com.example.demo.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Many;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;

import com.example.demo.entity.Teacher;

@Mapper
public interface StudentMapper {
	
	@Select("select * from t_student where age=#{age}")
	public List<Teacher> getTeachers(Map map);
	
	@Select("select * from t_student where age=#{age}")
	public List<Map> getTeachers1(Map map);
	
	@Select("select * from t_student where age=#{age}")
	public List<Map> getTeachers2(@Param("age") String ageaa);
	
	
	@Select("select * from t_student")
	@Results(id="user111",value= {
		@Result(column="name",javaType = String.class,property="name"),
		@Result(column="age",javaType = String.class,property="age"),
		@Result(column="tname",javaType = Teacher.class,property="teacher",
				many=@Many(select="com.example.demo.mapper.TeacherMapper.getTeacherBytname"))
	})
	public List<Map> getTeachers3(Map map);
	
	
}