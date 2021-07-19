package com.ex1.demo.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ex1.demo.dto.Article;
import com.ex1.demo.dto.ResultData;
import com.ex1.demo.service.ArticleService;
import com.ex1.demo.util.Util;

@Controller
public class MpaUsrArticleController {
	@Autowired
	private ArticleService articleService;
	
	@RequestMapping("/mpaUsr/home/main")
	public String aaaa() {
		return "mpaUsr/home/main";
	}
	
	@RequestMapping("mpaUsr/article/test")
	@ResponseBody
	public ResultData doWrite(int id, String title, String body, int boardId, int memberId) {
		articleService.doWrite(id, title, body, boardId,memberId);

		return new ResultData("S-1", id + "번 글이 작성되었습니다.", "작성된 글 내용", articleService.getArticleById(id));
	}

	@RequestMapping("mpaUsr/article/aa")
	@ResponseBody
	public Article aa(int id) {
		
		return articleService.getArticleById(id);
	}
}
