package com.ex1.demo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ex1.demo.dao.ReplyDao;
import com.ex1.demo.dto.Reply;
import com.ex1.demo.dto.ResultData;

@Service
public class ReplyService {
    @Autowired
    private ReplyDao replyDao;

    public ResultData write(String relTypeCode, int relId, int memberId, String body) {
        replyDao.write(relTypeCode, relId, memberId, body);
        int id = replyDao.getLastInsertId();

        return new ResultData("S-1", "댓글이 작성되었습니다.", "id", id);
    }
    
    public List<Reply> getForPrintRepliesByRelTypeCodeAndRelId(String relTypeCode, int relId) {
        return replyDao.getForPrintRepliesByRelTypeCodeAndRelId(relTypeCode, relId);
    }
    
    public Reply getReplyById(int id) {
        return replyDao.getReplyById(id);
    }

    public ResultData delete(int id) {
        replyDao.delete(id);

        return new ResultData("S-1", id + "번 댓글이 삭제되었습니다.", "id", id);
    }

	public void checkLike(String relTypeCode, int memberId, int replyId) {
		String check = replyDao.getLike(relTypeCode, memberId, replyId);
		if(check==null || check.equals("0")) {
			replyDao.checkLike(relTypeCode, memberId, replyId);
		}else {
			replyDao.uncheckLike(relTypeCode, memberId, replyId);
		}
		replyDao.updateLikeCount(replyId);
	}

	public void checkDislike(String relTypeCode, int memberId, int replyId) {
		String check = replyDao.getDislike(relTypeCode, memberId, replyId);
		if(check==null || check.equals("0")) {
			replyDao.checkDislike(relTypeCode, memberId, replyId);
		}else {
			replyDao.uncheckDislike(relTypeCode, memberId, replyId);
		}
		replyDao.updateLikeCount(replyId);
	}

	public boolean checkLikeTable(String relTypeCode, int memberId, int replyId) {
		if(replyDao.checkLikeTable(relTypeCode, memberId, replyId)==null) {
			return true;	//해당 멤버의 좋아요테이블이 없음
		}
		return false;
	}

	public void insertMember(String relTypeCode, int memberId, int replyId) {
		replyDao.insertMember(relTypeCode, memberId, replyId);
	}
}
