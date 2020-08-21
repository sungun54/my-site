package com.sbs.kys.mysite.controller;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;


import com.sbs.kys.mysite.dto.Article;
import com.sbs.kys.mysite.dto.Board;
import com.sbs.kys.mysite.dto.Member;
import com.sbs.kys.mysite.service.ArticleService;
import com.sbs.kys.mysite.util.Util;

@Controller
public class ArticleController {
	@Autowired
	private ArticleService articleService;
	
	
	@RequestMapping("/usr/article/{boardCode}-write")
	public String showWrite(@PathVariable("boardCode") String boardCode, Model model, String listUrl) {
		if ( listUrl == null ) {
			listUrl = "./" + boardCode + "-list";
		}
		model.addAttribute("listUrl", listUrl);
		Board board = articleService.getBoardByCode(boardCode);
		model.addAttribute("board", board);

		return "article/write";
	}
	
	@RequestMapping("/usr/article/{boardCode}-doWrite")
	public String doWrite(@RequestParam Map<String, Object> param, HttpServletRequest req, @PathVariable("boardCode") String boardCode, Model model) {
		Board board = articleService.getBoardByCode(boardCode);
		model.addAttribute("board", board);

		Map<String, Object> newParam = Util.getNewMapOf(param, "title", "body");
		int loginedMemberId = (int)req.getAttribute("loginedMemberId");
		newParam.put("boardId", board.getId());
		newParam.put("memberId", loginedMemberId);
		int newArticleId = articleService.write(newParam);

		String redirectUri = (String) param.get("redirectUri");
		redirectUri = redirectUri.replace("#id", newArticleId + "");
		return "redirect:" + redirectUri;
	}
	
	@RequestMapping("/usr/article/{boardCode}-list")
	public String showList(Model model, @PathVariable("boardCode") String boardCode) {
		Board board = articleService.getBoardByCode(boardCode);
		model.addAttribute("board", board);
		
		List<Article> articles = articleService.getForPrintArticles();

		model.addAttribute("articles", articles);

		return "article/list";
	}
	
	@RequestMapping("/usr/article/{boardCode}-detail")
	public String showDetail(Model model, @PathVariable("boardCode") String boardCode, @RequestParam Map<String, Object> param, HttpServletRequest req, String listUrl) {
		if ( listUrl == null ) {
			listUrl = "./" + boardCode + "-list";
		}
		
	
		
		Board board = articleService.getBoardByCode(boardCode);
		model.addAttribute("board", board);
		int id = Integer.parseInt((String) param.get("id"));
		
		articleService.hitUp(id);
		
		Member loginedMember = (Member)req.getAttribute("loginedMember");
		
		Article article = articleService.getForPrintArticle(loginedMember, id);
		
		
		
		model.addAttribute("article", article);
		model.addAttribute("listUrl", listUrl);
		
		return "article/detail";
	}
}
