package com.sbs.kys.mysite.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sbs.kys.mysite.dao.ArticleDao;
import com.sbs.kys.mysite.dto.Article;
import com.sbs.kys.mysite.dto.Board;
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
}
