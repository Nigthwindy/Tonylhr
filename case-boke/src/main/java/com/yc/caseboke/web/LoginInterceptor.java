package com.yc.caseboke.web;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.HandlerInterceptor;

import com.google.gson.Gson;
import com.yc.caseboke.vo.Result;

public class LoginInterceptor implements HandlerInterceptor {

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		if(request.getSession().getAttribute("loginedUser")==null){
		    //判断是否是Ajax请求
			if(request.getHeader("X-Requested-With") == null){
				response.sendRedirect("index?login");
			}else{
				Gson gson = new Gson();
				String json = gson.toJson(new Result(0,"请先登录系统！"));
				//让jquery将json字符串转换成json对象
				response.setContentType("application/json;charset=UTF-8");
				response.getWriter().append(json);
			}
			return false;
		} else {
			return true;
		}
	}

	
}
