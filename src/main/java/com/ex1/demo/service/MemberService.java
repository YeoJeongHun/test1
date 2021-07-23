package com.ex1.demo.service;

import java.util.Random;

import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.ex1.demo.dao.MemberDao;
import com.ex1.demo.dto.Member;
import com.ex1.demo.dto.ResultData;
import com.ex1.demo.util.Util;

@Service
public class MemberService {
    @Autowired
    private MailService mailService;

    @Value("${custom.siteMainUri}")
    private String siteMainUri;
    @Value("${custom.siteName}")
    private String siteName;

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

    public Member getMemberById(int id) {
        return memberDao.getMemberById(id);
    }

    public Member getMemberByNameAndEmail(String name, String email) {
        return memberDao.getMemberByNameAndEmail(name, email);
    }
    
    public ResultData notifyTempLoginPwByEmail(Member actor) {
        String title = "[" + siteName + "] 임시 패스워드 발송";
        String tempPassword = Util.getTempPassword(6);
        String body = "<h1>임시 패스워드 : " + tempPassword + "</h1>";
        body += "<a href=\"" + siteMainUri + "/mpaUsr/member/login\" target=\"_blank\">로그인 하러가기</a>";

        ResultData sendResultData = mailService.send(actor.getEmail(), title, body);

        if (sendResultData.isFail()) {
            return sendResultData;
        }

        tempPassword = Util.sha256(tempPassword);

        return new ResultData("S-1", "계정의 이메일주소로 임시 패스워드가 발송되었습니다.");
    }

    private void setTempPassword(Member actor, String tempPassword) {
        memberDao.modify(actor.getId(), tempPassword, null, null, null, null);
    }

    public ResultData modify(int id, String loginPw, String name, String nickname, String cellphoneNo, String email) {
        memberDao.modify(id, loginPw, name, nickname, cellphoneNo, email);

        return new ResultData("S-1", "회원정보가 수정되었습니다.", "id", id);
    }

	public void issueAuthKey(int id) {
        Random random = new Random();
        String authKey = random.toString();
        authKey = authKey.substring(authKey.lastIndexOf("@")+1);
		memberDao.issueAuthKey(id, authKey);
	}

	public String getAuthKey(int id) {
		return memberDao.getAuthKey(id);
	}
}