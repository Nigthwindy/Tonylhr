package com.yc.caseboke.biz;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.github.pagehelper.PageHelper;
import com.yc.caseboke.bean.Article;
import com.yc.caseboke.bean.ArticleExample;
import com.yc.caseboke.dao.ArticleMapper;

@Service
public class ArticleBiz extends BizException{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Autowired
	private ArticleMapper am;
	
	/**
	 * 查询最新发布的文章
	 * @param page
	 * @return
	 */
	public List<Article> queryNewArticle(int page){
		ArticleExample example = new ArticleExample();
		example.setOrderByClause("createTime desc");
		PageHelper.startPage(page,5);
		return am.selectByExampleWithBLOBs(example);
	}

	public List<Article> queryByCategory(int id, int page) {
		ArticleExample example = new ArticleExample();
		example.createCriteria().andCategoryidEqualTo(id);
		example.setOrderByClause("createTime desc");
		PageHelper.startPage(page,5);
		return am.selectByExampleWithBLOBs(example);
	}
	
	/**
	 * 阅读博文
	 */
	@Transactional //加入事务注解
	public Article read(int id){
		ArticleExample example = new ArticleExample();
		example.createCriteria().andIdEqualTo(id);
		Article a = am.selectByPrimaryKey(id);
		
		//更新阅读次数
		a.setReadcnt((a.getReadcnt() == null ? 0 : a.getReadcnt()) + 1); 
		am.updateByPrimaryKey(a);
		System.out.println("==------==="+a.getTitle()+a.getReadcnt());
		return a;
	}

	public List<Article> queryRela(Integer categoryid) {
		System.out.println("---------"+categoryid);
		ArticleExample example = new ArticleExample();
		//时间降序
		example.setOrderByClause("createTime desc");
		//查相关类别文章
		example.createCriteria().andCategoryidEqualTo(categoryid);
		//查10个记录
		PageHelper.startPage(1,10);
		return am.selectByExample(example);
	}
}
