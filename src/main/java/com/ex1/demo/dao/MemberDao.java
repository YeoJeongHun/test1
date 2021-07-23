package com.ex1.demo.dao;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.ex1.demo.dto.Member;

@Mapper
public interface MemberDao {
    Member getMemberByLoginId(@Param("loginId") String loginId);

    Member getMemberById(@Param("id") int id);

    void join(@Param("loginId") String loginId, @Param("loginPw") String loginPw, @Param("name") String name, @Param("nickname") String nickname, @Param("cellphoneNo") String cellphoneNo, @Param("email") String email);

    int getLastInsertId();
    
    Member getMemberByNameAndEmail(@Param("name") String name, @Param("email") String email);
    
    void modify(@Param("id") int id, @Param("loginPw") String loginPw, @Param("name") String name, @Param("nickname") String nickname, @Param("cellphoneNo") String cellphoneNo, @Param("email") String email);

	void issueAuthKey(@Param("id") int id, @Param("authKey") String authKey);

	String getAuthKey(@Param("id") int id);

}