package com.ex1.demo.dto;

import com.ex1.demo.util.Util;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class Member {
    private int id;
    private String regDate;
    private String updateDate;
    private String loginId;
    private String loginPw;
    private String name;
    private String nickname;
    private String cellphoneNo;
    private String email;
    private boolean delStatus;
    private String delDate;

    public String getAuthLevelName() {
        return "일반회원";
    }
} 