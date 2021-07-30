package com.ex1.demo.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.ex1.demo.dto.Reply;

@Mapper
public interface ReplyDao {
	void write(@Param("relTypeCode") String relTypeCode, @Param("relId") int relId, @Param("memberId") int memberId,
			@Param("body") String body);

	int getLastInsertId();

	List<Reply> getForPrintRepliesByRelTypeCodeAndRelId(@Param("relTypeCode") String relTypeCode,
			@Param("relId") int relId);
	
	Reply getReplyById(@Param("id") int id);

    void delete(@Param("id") int id);

	void checkLike(@Param("typeCode") String typeCode, @Param("memberId") int memberId,@Param("replyId") int replyId);

	void uncheckLike(@Param("typeCode") String typeCode, @Param("memberId") int memberId,@Param("replyId") int replyId);

	String getLike(@Param("typeCode") String typeCode, @Param("memberId") int memberId,@Param("replyId") int replyId);

	void checkDislike(@Param("typeCode") String typeCode, @Param("memberId") int memberId,@Param("replyId") int replyId);

	void uncheckDislike(@Param("typeCode") String typeCode, @Param("memberId") int memberId,@Param("replyId") int replyId);

	String getDislike(@Param("typeCode") String typeCode, @Param("memberId") int memberId,@Param("replyId") int replyId);

	String checkLikeTable(@Param("memberId") int memberId);

	void insertMember(@Param("typeCode") String typeCode, @Param("memberId") int memberId,@Param("replyId") int replyId);

	void updateLikeCount(@Param("id") int id);
}
