package com.ex1.demo.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ex1.demo.dto.Article;
import com.ex1.demo.dto.Board;
import com.ex1.demo.dto.ResultData;
import com.ex1.demo.service.ArticleService;

@Controller
public class MpaUsrArticleController {
	@Autowired
	private ArticleService articleService;

	private String msgAndBack(HttpServletRequest req, String msg) {
		req.setAttribute("msg", msg);
		return "mpaUsr/common/redirect";
	}

	@RequestMapping("/mpaUsr/article/list")
	public String showList(HttpServletRequest req, @RequestParam(defaultValue = "1") int boardId, String searchKeywordType, String searchKeyword,
			@RequestParam(defaultValue = "1") int page) {
		Board board = articleService.getBoardById(boardId);

		if (board == null) {
			return msgAndBack(req, boardId + "번 게시판이 존재하지 않습니다.");
		}

		req.setAttribute("board", board);

		int totalItemsCount = articleService.getArticlesTotalCount(boardId, searchKeywordType, searchKeyword);

		if ( searchKeyword == null || searchKeyword.trim().length() == 0 ) {

		}

		req.setAttribute("totalItemsCount", totalItemsCount);

		// 한 페이지에 보여줄 수 있는 게시물 최대 개수
		int itemsCountInAPage = 10;
		// 총 페이지 수
		int totalPage = (int) Math.ceil(totalItemsCount / (double) itemsCountInAPage);

		// 현재 페이지(임시)
		req.setAttribute("page", page);
		req.setAttribute("totalPage", totalPage);

		List<Article> articles = articleService.getForPrintArticles(boardId, searchKeywordType, searchKeyword, itemsCountInAPage, page);

		System.out.println("articles : " + articles);

		req.setAttribute("articles", articles);

		return "mpaUsr/article/list";
	}
	
	@RequestMapping("/mpaUsr/article/write")
	public String showWrite(HttpServletRequest req, @RequestParam(defaultValue = "1") int boardId) {
		Board board = articleService.getBoardById(boardId);

		if (board == null) {
			return msgAndBack(req, boardId + "번 게시판이 존재하지 않습니다.");
		}

		req.setAttribute("board", board);

		return "mpaUsr/article/write";
	}


}
