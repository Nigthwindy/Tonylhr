package com.yc.caseboke;

import java.util.Date;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.yc.caseboke.web.LoginInterceptor;

@SpringBootApplication
@RestController
@MapperScan("com.yc")
public class CaseBokeApplication implements WebMvcConfigurer {

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
	
	
	/**
	 * 注册拦截器，当容器启动时，该方法会被调用，那么注册拦截就会被加载到容器中
	 */
	@Override
	public void addInterceptors(InterceptorRegistry registry){
		registry.addInterceptor(new LoginInterceptor()).addPathPatterns("/toedit").addPathPatterns("/comment");
	}
}
