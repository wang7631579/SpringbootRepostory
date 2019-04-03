package com.example.demo.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import com.example.demo.entity.Teacher;

@Mapper
public interface TeacherMapper {
	@Select("select * from t_teacher where tname=#{tname}")
	public List<Teacher> getTeacherBytname(String tname);
	
	
	
}