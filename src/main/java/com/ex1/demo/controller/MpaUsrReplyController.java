package com.ex1.demo.controller;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ex1.demo.dto.Article;
import com.ex1.demo.dto.Reply;
import com.ex1.demo.dto.ResultData;
import com.ex1.demo.dto.Rq;
import com.ex1.demo.service.ArticleService;
import com.ex1.demo.service.ReplyService;
import com.ex1.demo.util.Util;

import lombok.extern.slf4j.Slf4j;

@Controller
@Slf4j
public class MpaUsrReplyController {
    @Autowired
    private ArticleService articleService;
    @Autowired
    private ReplyService replyService;
    
    @RequestMapping("/mpaUsr/reply/doDeleteAjax")
    @ResponseBody
    public ResultData doDeleteAjax(HttpServletRequest req, int id) {
        Reply reply = replyService.getReplyById(id);

        if ( reply == null ) {
            return new ResultData("F-1", "존재하지 않는 댓글입니다.");
        }

        Rq rq = (Rq)req.getAttribute("rq");

        if ( reply.getMemberId() != rq.getLoginedMemberId() ) {
            return new ResultData("F-1", "권한이 없습니다.");
        }

        replyService.delete(id);

        return new ResultData("S-1", String.format("%d번 댓글이 삭제되었습니다.", id));
    }
    
    @RequestMapping("/mpaUsr/reply/doDelete")
    public String doDelete(HttpServletRequest req, int id, String redirectUri) {
        Reply reply = replyService.getReplyById(id);

        if ( reply == null ) {
            return Util.msgAndBack(req, "존재하지 않는 댓글입니다.");
        }

        Rq rq = (Rq)req.getAttribute("rq");

        if ( reply.getMemberId() != rq.getLoginedMemberId() ) {
            return Util.msgAndBack(req, "권한이 없습니다.");
        }

        ResultData deleteResultData = replyService.delete(id);

        return Util.msgAndReplace(req, deleteResultData.getMsg(), redirectUri);
    }

    @RequestMapping("/mpaUsr/reply/doWrite")
    public String doWrite(HttpServletRequest req, String relTypeCode, int relId, String body, String redirectUri) {
        switch ( relTypeCode ) {
            case "article":
                Article article = articleService.getArticleById(relId);
                if ( article == null ) {
                    return Util.msgAndBack(req, "해당 게시물이 존재하지 않습니다.");
                }
                break;
            default:
                return Util.msgAndBack(req, "올바르지 않은 relTypeCode 입니다.");
        }

        Rq rq = (Rq)req.getAttribute("rq");

        int memberId = rq.getLoginedMemberId();

        ResultData writeResultData = replyService.write(relTypeCode, relId, memberId, body);

        return Util.msgAndReplace(req, writeResultData.getMsg(), redirectUri);
    }
    
    @RequestMapping("/mpaUsr/reply/doReplyModify")
    @ResponseBody
    public Map<String, Object> doReplyModify(HttpServletRequest req, int replyId, String body) {
        Rq rq = (Rq)req.getAttribute("rq");
        int memberId = rq.getLoginedMemberId();
        Reply reply = replyService.getReplyById(replyId);
        Map<String, Object> map = new HashMap<String, Object>();
        
        if(memberId != reply.getMemberId()) {
            map.put("result", 0);
            return map;
        }
        
        replyService.doReplyModify(replyId, body);
        
        map.put("result", 1);
        map.put("body", body);

        return map;
    }
    
    @RequestMapping("/mpaUsr/reply/likeCheckAjax")
    @ResponseBody
    public Map<String, Object> likeCheckAjax(HttpServletRequest req, int id, String like) {
    	Rq rq = (Rq)req.getAttribute("rq");
    	int memberId = rq.getLoginedMemberId();
    	int replyId = id;
    	if(replyService.checkLikeTable("reply", memberId, replyId)) {
    		replyService.insertMember("reply", memberId, replyId);
    	}
    	if(like.equals("up")) {
    		replyService.checkLike("reply", memberId, replyId);
    	}
    	else if(like.equals("down")) {
    		replyService.checkDislike("reply", memberId, replyId);
    	}
    	
    	Reply reply = replyService.getReplyById(replyId);
    	
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("likeCount", reply.getLikeCount());
		map.put("dislikeCount", reply.getDislikeCount());
		
		return map;
    }
}
