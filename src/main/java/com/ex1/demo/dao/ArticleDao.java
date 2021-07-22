package com.ex1.demo.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.ex1.demo.dto.Article;
import com.ex1.demo.dto.Board;

@Mapper
public interface ArticleDao {
	Article getArticleById(@Param("id") int id);

	void doWrite(@Param("id") int id, @Param("title") String title, @Param("body") String body, @Param("boardId") int boardId, @Param("memberId") int memberId);
	
	Board getBoardById(@Param("id") int id);
	
	int getArticlesTotalCount(@Param("boardId") int boardId,
			@Param("searchKeywordTypeCode") String searchKeywordTypeCode, @Param("searchKeyword") String searchKeyword);
	
	List<Article> getForPrintArticles(@Param("boardId") int boardId,
			@Param("searchKeywordTypeCode") String searchKeywordTypeCode, @Param("searchKeyword") String searchKeyword,
			@Param("limitFrom") int limitFrom, @Param("limitTake") int limitTake);
	
	void writeArticle(@Param("boardId") int boardId, @Param("memberId") int memberId, @Param("title") String title, @Param("body") String body);
}