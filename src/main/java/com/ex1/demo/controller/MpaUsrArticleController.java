package com.ex1.demo.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ex1.demo.dto.Article;
import com.ex1.demo.dto.Board;
import com.ex1.demo.dto.Member;
import com.ex1.demo.dto.Reply;
import com.ex1.demo.dto.ResultData;
import com.ex1.demo.dto.Rq;
import com.ex1.demo.service.ArticleService;
import com.ex1.demo.service.ReplyService;
import com.ex1.demo.util.Util;

import lombok.extern.slf4j.Slf4j;

@Controller
@Slf4j
public class MpaUsrArticleController {

    @Autowired
    private ArticleService articleService;
    
    @Autowired
    private ReplyService replyService;

    @RequestMapping("/mpaUsr/article/detail")
    public String showDetail(HttpServletRequest req, int id) {
    	int loginedMemberId = 0;
    	if(((Rq) req.getAttribute("rq")).isLogined()) {
    		loginedMemberId = ((Rq) req.getAttribute("rq")).getLoginedMemberId();
    		articleService.clickView(id, loginedMemberId);
    	}

    	Article article = articleService.getForPrintArticleById(id);
        if (article == null) {
            return Util.msgAndBack(req, id + "번 게시물이 존재하지 않습니다.");
        }
    	List<Reply> replies = replyService.getForPrintRepliesByRelTypeCodeAndRelId("article", id);
    	article.setRepliesCount(replies.size());
    	
    	int result = articleService.getClickLikeByMemberId("article", id, loginedMemberId);

        Board board = articleService.getBoardById(article.getBoardId());

        req.setAttribute("replies", replies);
        req.setAttribute("article", article);
        req.setAttribute("board", board);
        req.setAttribute("result", result);

        return "mpaUsr/article/detail";
    }
    
    @RequestMapping("/mpaUsr/article/write")
    public String showWrite(HttpServletRequest req, @RequestParam(defaultValue = "1") int boardId) {
        Board board = articleService.getBoardById(boardId);

        if (board == null) {
            return Util.msgAndBack(req, boardId + "번 게시판이 존재하지 않습니다.");
        }

        req.setAttribute("board", board);

        return "mpaUsr/article/write";
    }

    @RequestMapping("/mpaUsr/article/doWrite")
    public String doWrite(HttpServletRequest req, int boardId, int memberId, String title, String body) {
        if (Util.isEmpty(title)) {
            return Util.msgAndBack(req, "제목을 입력해주세요.");
        }

        if (Util.isEmpty(body)) {
            return Util.msgAndBack(req, "내용을 입력해주세요.");
        }

        ResultData writeArticleRd = articleService.writeArticle(boardId, memberId, title, body);

        if (writeArticleRd.isFail()) {
            return Util.msgAndBack(req, writeArticleRd.getMsg());
        }

        String replaceUri = "detail?id=" + writeArticleRd.getBody().get("id");
        return Util.msgAndReplace(req, writeArticleRd.getMsg(), replaceUri);
    }

    @RequestMapping("/mpaUsr/article/doModify")
    @ResponseBody
    public ResultData doModify(Integer id, String title, String body) {

        if (Util.isEmpty(id)) {
            return new ResultData("F-1", "번호를 입력해주세요.");
        }

        if (Util.isEmpty(title)) {
            return new ResultData("F-2", "제목을 입력해주세요.");
        }

        if (Util.isEmpty(body)) {
            return new ResultData("F-3", "내용을 입력해주세요.");
        }

        Article article = articleService.getArticleById(id);

        if (article == null) {
            return new ResultData("F-4", "존재하지 않는 게시물 번호입니다.");
        }

        return articleService.modifyArticle(id, title, body);
    }

    @RequestMapping("/mpaUsr/article/doDelete")
    public String doDelete(HttpServletRequest req, Integer id) {
        if (Util.isEmpty(id)) {
            return Util.msgAndBack(req, "id를 입력해주세요.");
        }

        ResultData rd = articleService.deleteArticleById(id);

        if (rd.isFail()) {
            return Util.msgAndBack(req, rd.getMsg());
        }

        String redirectUri = "../article/list?boardId=" + rd.getBody().get("boardId");

        return Util.msgAndReplace(req, rd.getMsg(), redirectUri);
    }

    @RequestMapping("/mpaUsr/article/list")
    public String showList(HttpServletRequest req, @RequestParam(defaultValue = "1") int boardId, String searchKeywordType, String searchKeyword,
                           @RequestParam(defaultValue = "1") int page) {
        Board board = articleService.getBoardById(boardId);

        if (Util.isEmpty(searchKeywordType)) {
            searchKeywordType = "titleAndBody";
        }

        if (board == null) {
            return Util.msgAndBack(req, boardId + "번 게시판이 존재하지 않습니다.");
        }

        req.setAttribute("board", board);

        int totalItemsCount = articleService.getArticlesTotalCount(boardId, searchKeywordType, searchKeyword);

        if (searchKeyword == null || searchKeyword.trim().length() == 0) {

        }

        req.setAttribute("totalItemsCount", totalItemsCount);

        // 한 페이지에 보여줄 수 있는 게시물 최대 개수
        int itemsCountInAPage = 5;
        // 총 페이지 수
        int totalPage = (int) Math.ceil(totalItemsCount / (double) itemsCountInAPage);

        // 현재 페이지(임시)
        req.setAttribute("page", page);
        req.setAttribute("totalPage", totalPage);

        List<Article> articles = articleService.getForPrintArticles(boardId, searchKeywordType, searchKeyword, itemsCountInAPage, page);

        req.setAttribute("articles", articles);

        return "mpaUsr/article/list";
    }

    @RequestMapping("/mpaUsr/article/getArticle")
    @ResponseBody
    public ResultData getArticle(Integer id) {
        if (Util.isEmpty(id)) {
            return new ResultData("F-1", "번호를 입력해주세요.");
        }

        Article article = articleService.getArticleById(id);

        if (article == null) {
            return new ResultData("F-1", id + "번 글은 존재하지 않습니다.", "id", id);
        }

        return new ResultData("S-1", article.getId() + "번 글 입니다.", "article", article);
    }
    
    @RequestMapping("/mpaUsr/article/revise")
    public String reviseArticle(HttpServletRequest req, int articleId) {
    	Article article = articleService.getArticleById(articleId);
    	String body = article.getBody();
    	String title = article.getTitle();
    	int boardId = article.getBoardId();
    	
    	req.setAttribute("articleId", articleId);
    	req.setAttribute("body", body);
    	req.setAttribute("title", title);
    	req.setAttribute("boardId", boardId);
    	
    	return "mpaUsr/article/revise";
    }
    
    @RequestMapping("/mpaUsr/article/doRevise")
    public String doRevise(HttpServletRequest req, int boardId, int memberId, int articleId, String title, String body) {
    	int loginedMemberId = ((Rq) req.getAttribute("rq")).getLoginedMemberId();
    	
    	if(articleService.getMemberIdByArticleId(articleId)!=loginedMemberId) {
    		return Util.msgAndBack(req, "작성자만 수정가능합니다.");
    	}
        articleService.doRevise(boardId, memberId, articleId, title, body);
    	
        String replaceUri = "detail?id=" + articleId;
        return Util.msgAndReplace(req, "글이 수정되었습니다.", replaceUri);
    }
    
    @RequestMapping("/mpaUsr/article/articleLikeAjax")
    @ResponseBody
    public Map<String, Object> likeCheckAjax(HttpServletRequest req, int memberId, int articleId) {
		int result = articleService.memberClickedLike("article", articleId, memberId);
    	int articleLikeCount = articleService.getArticleLikeCount("article", articleId);
    	
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("likeCount", articleLikeCount);
		map.put("result", result);
		
		return map;
    }
}
