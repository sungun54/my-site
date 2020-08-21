package com.sbs.kys.mysite.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.sbs.kys.mysite.dto.Article;
import com.sbs.kys.mysite.dto.Board;

@Mapper
public interface ArticleDao {
	Board getBoardByCode(String boardCode);

	void write(Map<String, Object> param);

	List<Article> getForPrintArticles();

	Article getForPrintArticle(@Param("id") int id);

	void hitUp(int id);
}
