package com.ex1.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ex1.demo.dao.MemberDao;
import com.ex1.demo.dto.Member;
import com.ex1.demo.dto.ResultData;

@Service
public class MemberService {
    @Autowired
    private MemberDao memberDao;

    public Member getMemberByLoginId(String loginId) {
        return memberDao.getMemberByLoginId(loginId);
    }

    public ResultData join(String loginId, String loginPw, String name, String nickname, String cellphoneNo, String email) {
        memberDao.join(loginId, loginPw, name, nickname, cellphoneNo, email);
        int id = memberDao.getLastInsertId();

        return new ResultData("S-1", "회원가입이 완료되었습니다.", "id", id);
    }
}