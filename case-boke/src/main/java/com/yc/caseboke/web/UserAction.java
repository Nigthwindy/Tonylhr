package com.yc.caseboke.web;

import javax.annotation.Resource;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.yc.caseboke.bean.User;
import com.yc.caseboke.biz.BizException;
import com.yc.caseboke.biz.UserBiz;
import com.yc.caseboke.vo.Result;

@RestController
@SessionAttributes("loginedUser")
public class UserAction {

	@Resource
	private UserBiz ub;
	
	@PostMapping("login")
	public Result login(String name,String password,Model model){
		try{
			User user = ub.login(name, password);
			model.addAttribute("loginedUser",user);
			return new Result(1,"OK",user);
		}catch (BizException e){
			//给用户看
			return new Result(0,e.getMessage());
		}catch(RuntimeException e){
			e.printStackTrace();
			//掩饰错误
			return new Result(0,"业务繁忙，请稍后再试！");
		}
	}
}
