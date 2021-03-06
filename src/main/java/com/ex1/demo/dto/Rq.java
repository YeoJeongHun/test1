package com.ex1.demo.dto;

import java.util.Map;

import com.ex1.demo.util.Util;

import lombok.Getter;

public class Rq {
    @Getter
    private String currentUrl;
    private String currentUri;
    private Member loginedMember;
    private Map<String, String> paramMap;
    @Getter
    private boolean needToChangePassword;

    public Rq(Member loginedMember, String currentUri, Map<String, String> paramMap, boolean needToChangePassword) {
        this.loginedMember = loginedMember;
        this.currentUrl = currentUri.split("\\?")[0];
        this.currentUri = currentUri;
        this.paramMap = paramMap;
        this.needToChangePassword = needToChangePassword;
    }

    public boolean isLogined() {
        return loginedMember != null;
    }

    public boolean isNotLogined() {
        return isLogined() == false;
    }

    public int getLoginedMemberId() {
        if (isNotLogined()) return 0;

        return loginedMember.getId();
    }

    public Member getLoginedMember() {
        return loginedMember;
    }

    public String getEncodedCurrentUri() {
        return Util.getUriEncoded(getCurrentUri());
    }

    public String getCurrentUri() {
        return currentUri;
    }

    public String getLoginPageUri() {
        String afterLoginUri;

        if (isLoginPage()) {
        	afterLoginUri = Util.getUriEncoded(paramMap.get("afterLoginUri"));
        } else {
            afterLoginUri = getEncodedCurrentUri();
        }

        return "../member/login?afterLoginUri=" + afterLoginUri;
    }

    private boolean isLoginPage() {
        return currentUrl.equals("/mpaUsr/member/login");
    }
    
    public String getWriterProfileImgUri() {
    	if(Util.existsProfile(getLoginedMemberId())) {
    		return Util.getProfilePass(getLoginedMemberId());
    	}
        return "/file/profile/기본이미지.jpg";
    }
}