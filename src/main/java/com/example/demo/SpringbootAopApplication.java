package com.example.demo;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.ImportResource;
import org.springframework.scheduling.annotation.EnableAsync;

@SpringBootApplication
@MapperScan("com.example.demo.mapper")
//@ImportResource("classpath:transaction.xml")
public class SpringbootAopApplication {
	
	public static void main(String[] args) {
		//System.out.println(dataSource);
		SpringApplication.run(SpringbootAopApplication.class, args);
	}

}
