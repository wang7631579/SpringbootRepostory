package com.example.demo.mapper;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.example.demo.entity.User;

@Mapper
public interface UserMapper {
	@Select("select * from t_user where userName=#{name}")
	User getUserByName(String name);
	
	@Insert("INSERT INTO t_user (userName, userAge) VALUES (#{userName}, #{userAge})")
	void insertUser(User user);

	@Update("UPDATE t_user set userName = #{userName} where userAge = #{userAge} ")
	int  updateUser(User user);
	
	
	@Delete("delete from  t_user  where userAge = #{userAge} ")
	int  deleteUser(User user);
	
	/*@Update("update tb_product set product_name=#{productName},product_desc=#{productDesc} WHERE product_id=#{productId}")
	int updateProduct(Product product);

	@Delete("delete from tb_product where product_id=#{id}")
	void deleteProductById(Long id);

	@Select("select * from tb_product where product_name=#{productName}")
	Product getProductByName(String productName);*/
}