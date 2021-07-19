package com.ex1.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ex1.demo.dao.ArticleDao;
import com.ex1.demo.dto.Article;

@Service
public class ArticleService {
	@Autowired
	private ArticleDao articleDao;

	public Article getArticleById(int id) {
		return articleDao.getArticleById(id);
	}

	public void doWrite(int id, String title, String body, int boardId, int memberId) {
		articleDao.doWrite(id, title, body, boardId, memberId);
	}
	
}
