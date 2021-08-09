package com.ex1.demo.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.ex1.demo.dto.Article;

@Mapper
public interface HomeDao {

	List<Article> recentArticlesInNotice();

	List<Article> topArticlesInNotice();

	List<Article> recentArticlesInFree();

	List<Article> topArticlesInFree();
}
