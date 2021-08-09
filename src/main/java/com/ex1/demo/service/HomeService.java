package com.ex1.demo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ex1.demo.dao.HomeDao;
import com.ex1.demo.dto.Article;

@Service
public class HomeService {
	@Autowired
	private HomeDao homeDao;

	public List<Article> recentArticlesInNotice() {
		return homeDao.recentArticlesInNotice();
	}

	public List<Article> topArticlesInNotice() {
		return homeDao.topArticlesInNotice();
	}

	public List<Article> recentArticlesInFree() {
		return homeDao.recentArticlesInFree();
	}

	public List<Article> topArticlesInFree() {
		return homeDao.topArticlesInFree();
	}
	
}