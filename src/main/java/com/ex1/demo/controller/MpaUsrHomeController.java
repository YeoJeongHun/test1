package com.ex1.demo.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.ex1.demo.dto.Article;
import com.ex1.demo.service.ArticleService;
import com.ex1.demo.service.HomeService;

@Controller
public class MpaUsrHomeController {
	@Autowired
	private HomeService homeService;
	
	@RequestMapping("/")
	public String showMainRoot() {
		return "redirect:/mpaUsr/home/main";
	}

	@RequestMapping("/mpaUsr/home/main")
	public String showMain(HttpServletRequest req) {
		List<Article> recentArticlesInNotice = homeService.recentArticlesInNotice();
		List<Article> topArticlesInNotice = homeService.topArticlesInNotice();
		List<Article> recentArticlesInFree = homeService.recentArticlesInFree();
		List<Article> topArticlesInFree = homeService.topArticlesInFree();
	
		req.setAttribute("recentArticlesInNotice", recentArticlesInNotice);
		req.setAttribute("topArticlesInNotice", topArticlesInNotice);
		req.setAttribute("recentArticlesInFree", recentArticlesInFree);
		req.setAttribute("topArticlesInFree", topArticlesInFree);
		
		return "mpaUsr/home/main";
	}

}
