package com.ex1.demo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ex1.demo.dao.ArticleDao;
import com.ex1.demo.dto.Article;
import com.ex1.demo.dto.Board;

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

	public Board getBoardById(int id) {
		return articleDao.getBoardById(id);
	}

	public int getArticlesTotalCount(int boardId, String searchKeywordTypeCode, String searchKeyword) {
		if (searchKeyword != null && searchKeyword.length() == 0) {
			searchKeyword = null;
		}

		return articleDao.getArticlesTotalCount(boardId, searchKeywordTypeCode, searchKeyword);
	}

	public List<Article> getForPrintArticles(int boardId, String searchKeywordTypeCode, String searchKeyword,
			int itemsCountInAPage, int page) {
		if (searchKeyword != null && searchKeyword.length() == 0) {
			searchKeyword = null;
		}
		int limitFrom = (page - 1) * itemsCountInAPage;
		int limitTake = itemsCountInAPage;

		return articleDao.getForPrintArticles(boardId, searchKeywordTypeCode, searchKeyword, limitFrom, limitTake);
	}

}
