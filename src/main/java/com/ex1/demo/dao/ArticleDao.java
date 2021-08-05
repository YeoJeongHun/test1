package com.ex1.demo.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.ex1.demo.dto.Article;
import com.ex1.demo.dto.Board;

@Mapper
public interface ArticleDao {
    boolean modifyArticle(@Param("id") int id, @Param("title") String title, @Param("body") String body);

    void writeArticle(@Param("boardId") int boardId, @Param("memberId") int memberId, @Param("title") String title,
                      @Param("body") String body);
    
    Article getArticleById(@Param("id") int id);

	int getLastInsertId();

	void deleteArticleById(@Param("id") int id);

	Board getBoardById(@Param("id") int id);

	int getArticlesTotalCount(@Param("boardId") int boardId,
			@Param("searchKeywordTypeCode") String searchKeywordTypeCode, @Param("searchKeyword") String searchKeyword);

	List<Article> getForPrintArticles(@Param("boardId") int boardId,
			@Param("searchKeywordTypeCode") String searchKeywordTypeCode, @Param("searchKeyword") String searchKeyword,
			@Param("limitFrom") int limitFrom, @Param("limitTake") int limitTake);
	
	Article getForPrintArticleById(@Param("id") int id);

	int getArticleLikeCount(@Param("code") String code, @Param("articleId") int articleId);

	int memberClickedLike(@Param("code") String code, @Param("articleId") int articleId, @Param("memberId") int memberId);

	void setMemberClickLike(@Param("code") String code, @Param("articleId") int articleId, @Param("memberId") int memberId, @Param("checkLike") int checkLike);

	int getClickLikeByMemberId(@Param("code") String code, @Param("id") int id, @Param("loginedMemberId") int loginedMemberId);

	int searchClickLike(@Param("code") String code, @Param("articleId") int articleId, @Param("memberId") int memberId);

	void insertClickLike(@Param("code") String code, @Param("articleId") int articleId, @Param("memberId") int memberId);

	void recountArticleLike(@Param("articleId") int articleId);

	void doRevise(@Param("boardId") int boardId, @Param("memberId") int memberId, @Param("articleId") int articleId, @Param("title") String title, @Param("body") String body);

	int getMemberIdByArticleId(@Param("articleId") int articleId);

	void setArticleViewCount(@Param("articleId") int articleId);

	int checkClickView(@Param("articleId") int articleId, @Param("loginedMemberId") int loginedMemberId);

	void setClickView(@Param("articleId") int articleId, @Param("loginedMemberId") int loginedMemberId);
}