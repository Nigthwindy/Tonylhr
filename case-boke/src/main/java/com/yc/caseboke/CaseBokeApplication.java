package com.yc.caseboke;

import java.util.Date;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@RestController
@MapperScan("com.yc")
public class CaseBokeApplication {

	public static void main(String[] args) {
		SpringApplication.run(CaseBokeApplication.class, args);
	}

	@GetMapping("hello")
	public String hello(){
		return "hello";
	}
	
	@Bean
	public Date getNow(){
		return new Date();
	}
}
