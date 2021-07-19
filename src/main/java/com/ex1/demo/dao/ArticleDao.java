package com.ex1.demo.dao;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.ex1.demo.dto.Article;

@Mapper
public interface ArticleDao {
	Article getArticleById(@Param("id") int id);

	void doWrite(@Param("id") int id, @Param("title") String title, @Param("body") String body, @Param("boardId") int boardId, @Param("memberId") int memberId);
}
