package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.example.demo.service.AsyncService;

@Controller
@EnableAsync
public class AsyncController {
	
	@Autowired
	AsyncService asyncService;

	@GetMapping("/asycnHello")
	@ResponseBody
	public String asycnHello(String userName) {
		System.out.println(Thread.currentThread().getName());
		asyncService.testAsync();
		return "helloWorld";
	}
	
	
}
