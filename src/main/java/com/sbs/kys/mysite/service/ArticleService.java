package com.sbs.kys.mysite.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sbs.kys.mysite.dao.ArticleDao;
import com.sbs.kys.mysite.dto.Article;
import com.sbs.kys.mysite.dto.Board;
import com.sbs.kys.mysite.dto.Member;
import com.sbs.kys.mysite.util.Util;

@Service
public class ArticleService {
	@Autowired
	private ArticleDao articleDao;

	public Board getBoardByCode(String boardCode) {
		return articleDao.getBoardByCode(boardCode);
	}

	public int write(Map<String, Object> param) {
		articleDao.write(param);
		int id = Util.getAsInt(param.get("id"));

		return id;
	}

	public List<Article> getForPrintArticles() {
		List<Article> articles = articleDao.getForPrintArticles();

		return articles;
	}

	public Article getForPrintArticle(Member loginedMember, int id) {	
		Article article = articleDao.getForPrintArticle(id);
		updateForPrintInfo(loginedMember, article);
		
		return article;
	}
	
	private void updateForPrintInfo(Member actor, Article article) {
		Util.putExtraVal(article, "actorCanDelete", actorCanDelete(actor, article));
		Util.putExtraVal(article, "actorCanModify", actorCanModify(actor, article));
	}
	
	public boolean actorCanModify(Member actor, Article article) {
		return actor != null && actor.getId() == article.getMemberId() ? true : false;
	}

	// 액터가 해당 댓글을 삭제할 수 있는지 알려준다.
	public boolean actorCanDelete(Member actor, Article article) {
		return actorCanModify(actor, article);
	}

	public void hitUp(int id) {
		articleDao.hitUp(id);
	}
}
