package com.yc.caseboke.biz;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.yc.caseboke.bean.Category;
import com.yc.caseboke.dao.CategoryMapper;

@Service
public class CategoryBiz {

	@Resource
	private CategoryMapper cm;
	
	public List<Category> queryAll(){
		return cm.selectByExample(null);
	}
}
