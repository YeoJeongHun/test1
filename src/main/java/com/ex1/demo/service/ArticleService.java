package com.ex1.demo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ex1.demo.dao.ArticleDao;
import com.ex1.demo.dto.Article;
import com.ex1.demo.dto.Board;
import com.ex1.demo.dto.ResultData;

@Service
public class ArticleService {
	@Autowired
	private ArticleDao articleDao;

	public ResultData modifyArticle(int id, String title, String body) {
		Article article = getArticleById(id);

		if (isEmpty(article)) {
			return new ResultData("F-1", "존재하지 않는 게시물 번호입니다.", "id", id);
		}

		articleDao.modifyArticle(id, title, body);

		return new ResultData("S-1", "게시물이 수정되었습니다.", "id", id);
	}

	private boolean isEmpty(Article article) {
		if (article == null) {
			return true;
		} else if (article.isDelStatus()) {
			return true;
		}

		return false;
	}

	public ResultData deleteArticleById(int id) {
		Article article = getArticleById(id);

		if (isEmpty(article)) {
			return new ResultData("F-1", "게시물이 존재하지 않습니다.", "id", id);
		}

		articleDao.deleteArticleById(id);

		return new ResultData("S-1", id + "번 게시물이 삭제되었습니다.", "id", id, "boardId", article.getBoardId());
	}

	public ResultData writeArticle(int boardId, int memberId, String title, String body) {
		articleDao.writeArticle(boardId, memberId, title, body);
		int id = articleDao.getLastInsertId();

		return new ResultData("S-1", "게시물이 작성되었습니다.", "id", id);
	}

	public Article getArticleById(int id) {
		return articleDao.getArticleById(id);
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
	
	public Article getForPrintArticleById(int id) {
		return articleDao.getForPrintArticleById(id);
	}

	public int getArticleLikeCount(String code, int articleId) {
		return articleDao.getArticleLikeCount(code, articleId);
	}
	
	public int memberClickedLike(String code, int articleId, int memberId) {
		if(articleDao.searchClickLike(code, articleId, memberId)>0) {
			return memberClickedLike_sub(code, articleId, memberId);
		}
		articleDao.insertClickLike(code, articleId, memberId);
		return memberClickedLike_sub(code, articleId, memberId);
	}
	
	private int memberClickedLike_sub(String code, int articleId, int memberId) {
		if(articleDao.memberClickedLike(code, articleId, memberId)>0) {
			articleDao.setMemberClickLike(code, articleId, memberId, 0);
			articleDao.recountArticleLike(articleId);
			return 0;
		}
		articleDao.setMemberClickLike(code, articleId, memberId, 1);
		articleDao.recountArticleLike(articleId);
		return 1;
	}

	public int getClickLikeByMemberId(String code, int id, int loginedMemberId) {
		return articleDao.getClickLikeByMemberId(code, id, loginedMemberId);
	}

	public void doRevise(int boardId, int memberId, int articleId, String title, String body) {
		articleDao.doRevise(boardId, memberId, articleId, title, body);
	}

	public int getMemberIdByArticleId(int articleId) {
		return articleDao.getMemberIdByArticleId(articleId);
	}

	public void clickView(int articleId, int loginedMemberId) {
		if(articleDao.checkClickView(articleId, loginedMemberId)==0) {
			articleDao.setClickView(articleId, loginedMemberId);
			articleDao.setArticleViewCount(articleId);
		}
	}
	
}