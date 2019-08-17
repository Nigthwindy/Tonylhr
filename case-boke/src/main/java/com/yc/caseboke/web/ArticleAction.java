package com.yc.caseboke.web;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestParam;

import com.yc.caseboke.bean.Article;
import com.yc.caseboke.bean.Category;
import com.yc.caseboke.biz.ArticleBiz;
import com.yc.caseboke.biz.CategoryBiz;

@Controller
public class ArticleAction {

	@Resource
	private ArticleBiz abiz;
	
	@Resource
	private CategoryBiz cbiz;
	
	@ModelAttribute("cList")
	public List<Category> init(){
		return cbiz.queryAll();
	}
	//查询首页
	@GetMapping("index")
	public String index(@RequestParam(defaultValue="1") int page, Model model){
		//最新文章
		model.addAttribute("aList",abiz.queryNewArticle(page));
		return "index";
	}
	//分类文章
	@GetMapping("category")
	public String category(@RequestParam(defaultValue="1") int page,
			int id, Model model){
		model.addAttribute("aList",abiz.queryByCategory(id,page));
		return "category";
	}
	
	//显示文件
	@GetMapping("article")
	public String article(int id,Model model){
		System.out.println("========="+id);
		Article a = abiz.read(id);
		List<Article> relaList = abiz.queryRela(a.getCategoryid());
		model.addAttribute("relaList",relaList);
		model.addAttribute(a);
		return "article";
	}
}
