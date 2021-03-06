package com.yc.caseboke.biz;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.yc.caseboke.bean.Article;
import com.yc.caseboke.bean.Comment;
import com.yc.caseboke.dao.ArticleMapper;
import com.yc.caseboke.dao.CommentMapper;

@Service
public class CommentBiz {

	@Resource
	private ArticleMapper am;
	
	@Resource
	private CommentMapper cm;
	
	//添加评论数
	@Transactional
	public Comment comment(Comment comm){
		cm.insertSelective(comm);
		//文章评论数更新
		Article a = am.selectByPrimaryKey(comm.getArticleid());
		a.setCommcnt((a.getCommcnt() == null ? 0 : a.getCommcnt())+1);
		am.updateByPrimaryKey(a);
		return comm;	
	}
}
