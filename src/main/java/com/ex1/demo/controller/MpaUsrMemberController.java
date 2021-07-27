package com.ex1.demo.controller;

import java.util.Map;
import java.util.Random;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartRequest;

import com.ex1.demo.dto.Member;
import com.ex1.demo.dto.ResultData;
import com.ex1.demo.dto.Rq;
import com.ex1.demo.service.MemberService;
import com.ex1.demo.util.Util;

import lombok.extern.slf4j.Slf4j;

@Controller
@Slf4j
public class MpaUsrMemberController {
	@Autowired
    private MemberService memberService;

	// checkPasswordAuthCode : 체크비밀번호인증코드
    @RequestMapping("/mpaUsr/member/modify")
    public String showModify(HttpServletRequest req) {
        return "mpaUsr/member/modify";
    }

    @RequestMapping("/mpaUsr/member/doModify")
    public String doModify(HttpServletRequest req, MultipartRequest multipartRequest, String loginPw, String name, String
            nickname, String cellphoneNo, String email, String authKey) {

        if ( loginPw != null && loginPw.trim().length() == 0 ) {
            loginPw = null;
        }

        int id = ((Rq)req.getAttribute("rq")).getLoginedMemberId();
    	if(!authKey.equals(memberService.getAuthKey(id))) {
    		return Util.msgAndBack(req, authKey+"인증키가 일치하지 않습니다.");
    	}

        ResultData modifyRd = memberService.modify(id, loginPw, name, nickname, cellphoneNo, email);

        if (modifyRd.isFail()) {
            return Util.msgAndBack(req, modifyRd.getMsg());
        }
        //프로필 이미지 수정
        switch(Util.getUserProfileImg(req, multipartRequest, id)) {
    		case "F-1" : return Util.msgAndBack(req, "프로필용 파일 전환에 실패했습니다. 마이페이지에서 다시 등록해주세요.");
    		case "F-2" : return Util.msgAndBack(req, "프로필용 파일은 최대 50KB까지 입니다.");
    		case "F-3" : return Util.msgAndBack(req, "기본 이미지 세팅 실패");
    		case "F-4" : return Util.msgAndBack(req, "파일 업로드 실패");
    		case "S-2" : memberService.setImgFile("modify", "member", id, "basic", "jpg", 44914, Util.getProfileDirPass(id));
    			return Util.msgAndBack(req, "회원정보 수정 완료! 기본이미지로 프로필 설정");
    		default : 
        	String originFileName = null, fileExt = null, fileDir = null;
        	int fileSize = 0;
        	Map<String, MultipartFile> fileMap = multipartRequest.getFileMap();
    		for (String fileInputName : fileMap.keySet()) {
    			MultipartFile multipartFile = fileMap.get(fileInputName);
    			originFileName = multipartFile.getOriginalFilename();
    			fileExt = Util.getFileExtFromFileName(multipartFile.getOriginalFilename());
    			fileSize = (int)multipartFile.getSize();
    			fileDir = Util.getProfileDirPass(id);
        		memberService.setImgFile("modify", "member", id, originFileName, fileExt, fileSize, fileDir);
    		}
        }
        //인증키 삭제하기
        memberService.delAuthKey(authKey);

        return Util.msgAndReplace(req, modifyRd.getMsg(), "/");
    }

    @RequestMapping("/mpaUsr/member/checkPassword")
    public String showCheckPassword(HttpServletRequest req) {
        return "mpaUsr/member/checkPassword";
    }

    @RequestMapping("/mpaUsr/member/doCheckPassword")
    public String doCheckPassword(HttpServletRequest req, String loginPw, String redirectUri) {
        Member loginedMember = ((Rq) req.getAttribute("rq")).getLoginedMember();

        if (loginedMember.getLoginPw().equals(loginPw) == false) {
            return Util.msgAndBack(req, "비밀번호가 일치하지 않습니다.");
        }
        memberService.issueAuthKey(loginedMember.getId());
        req.setAttribute("authKey", memberService.getAuthKey(loginedMember.getId()));

        return "mpaUsr/member/modify";
    }
    
	@RequestMapping("/mpaUsr/member/mypage")
    public String showMypage(HttpServletRequest req) {
        return "mpaUsr/member/mypage";
    }

    @RequestMapping("/mpaUsr/member/login")
    public String showLogin(HttpServletRequest req) {
        return "mpaUsr/member/login";
    }
    
    @RequestMapping("/mpaUsr/member/findLoginPw")
    public String showFindLoginPw(HttpServletRequest req) {
        return "mpaUsr/member/findLoginPw";
    }

    @RequestMapping("/mpaUsr/member/doFindLoginPw")
    public String doFindLoginPw(HttpServletRequest req, String loginId, String name, String email, String redirectUri) {
        if (Util.isEmpty(redirectUri)) {
            redirectUri = "/";
        }

        Member member = memberService.getMemberByLoginId(loginId);

        if (member == null) {
            return Util.msgAndBack(req, "일치하는 회원이 존재하지 않습니다.");
        }

        if (member.getName().equals(name) == false) {
            return Util.msgAndBack(req, "일치하는 회원이 존재하지 않습니다.");
        }

        if (member.getEmail().equals(email) == false) {
            return Util.msgAndBack(req, "일치하는 회원이 존재하지 않습니다.");
        }

        ResultData notifyTempLoginPwByEmailRs = memberService.notifyTempLoginPwByEmail(member);

        return Util.msgAndReplace(req, notifyTempLoginPwByEmailRs.getMsg(), redirectUri);
    }

    @RequestMapping("/mpaUsr/member/findLoginId")
    public String showFindLoginId(HttpServletRequest req) {
        return "mpaUsr/member/findLoginId";
    }

    @RequestMapping("/mpaUsr/member/doFindLoginId")
    public String doFindLoginId(HttpServletRequest req, String name, String email, String redirectUri) {
        if (Util.isEmpty(redirectUri)) {
            redirectUri = "/";
        }

        Member member = memberService.getMemberByNameAndEmail(name, email);

        if (member == null) {
            return Util.msgAndBack(req, "일치하는 회원이 존재하지 않습니다.");
        }

        return Util.msgAndBack(req, String.format("회원님의 아이디는 `%s` 입니다.", member.getLoginId()));
    }
    
    @RequestMapping("/mpaUsr/member/doLogout")
    public String doLogout(HttpServletRequest req, HttpSession session) {
        session.removeAttribute("loginedMemberId");

        String msg = "로그아웃 되었습니다.";
        return Util.msgAndReplace(req, msg, "/");
    }

    @RequestMapping("/mpaUsr/member/doLogin")
    public String doLogin(HttpServletRequest req, HttpSession session, String loginId, String loginPw, String redirectUri) {
        if ( Util.isEmpty(redirectUri) ) {
            redirectUri = "/";
        }

        Member member = memberService.getMemberByLoginId(loginId);

        if (member == null) {
            return Util.msgAndBack(req, loginId + "(은)는 존재하지 않는 로그인아이디 입니다.");
        }

        if (member.getLoginPw().equals(loginPw) == false) {
            return Util.msgAndBack(req, "비밀번호가 일치하지 않습니다.");
        }

        //HttpSession session = req.getSession();
        session.setAttribute("loginedMemberId", member.getId());
        
        String msg = "환영합니다.";
        if(memberService.checkTooLongUsingPw(member.getId())) {
        	msg = "현재 비밀번호를 사용한지 90일이 지났습니다. 비밀번호를 변경해주세요.";
        }
        return Util.msgAndReplace(req, msg, redirectUri);
    }

    @RequestMapping("/mpaUsr/member/join")
    public String showJoin(HttpServletRequest req) {
        return "mpaUsr/member/join";
    }

    @RequestMapping("/mpaUsr/member/doJoin")
    public String doJoin(HttpServletRequest req, MultipartRequest multipartRequest, String loginId, String loginPw, String name, String nickname, String cellphoneNo, String email) {
        Member oldMember = memberService.getMemberByLoginId(loginId);

        if (oldMember != null) {
            return Util.msgAndBack(req, loginId + "(은)는 이미 사용중인 로그인아이디 입니다.");
        }

        ResultData joinRd = memberService.join(loginId, loginPw, name, nickname, cellphoneNo, email);

        if (joinRd.isFail()) {
            return Util.msgAndBack(req, joinRd.getMsg());
        }
        
        //프로필 이미지 설정
        Map<String, Object> map = joinRd.getBody();
        int id = (int)map.get("id");
        String msg;
        switch(Util.getUserProfileImg(req, multipartRequest, id)) {
        	case "F-1" : msg = "프로필용 파일 전환에 실패했습니다. 마이페이지에서 다시 등록해주세요."; break;
        	case "F-2" : msg = "프로필용 파일은 최대 50KB까지 입니다."; break;
        	case "F-3" : msg = "기본 이미지 세팅 실패"; break;
        	case "F-4" : msg = "파일 업로드 실패"; break;
        	case "S-2" : memberService.setImgFile("join", "member", id, "basic", "jpg", 44914, Util.getProfileDirPass(id));
				msg = "회원가입 완료! 기본이미지로 프로필 설정"; break;
        	default : msg = joinRd.getMsg();
            	String originFileName = null, fileExt = null, fileDir = null;
            	int fileSize = 0;
            	Map<String, MultipartFile> fileMap = multipartRequest.getFileMap();
        		for (String fileInputName : fileMap.keySet()) {
        			MultipartFile multipartFile = fileMap.get(fileInputName);
        			originFileName = multipartFile.getOriginalFilename();
        			fileExt = Util.getFileExtFromFileName(multipartFile.getOriginalFilename());
        			fileSize = (int)multipartFile.getSize();
        			fileDir = Util.getProfileDirPass(id);
            		memberService.setImgFile("join", "member", id, originFileName, fileExt, fileSize, fileDir);
        		}
        }
        
        return Util.msgAndReplace(req, msg, "/");
    }
}