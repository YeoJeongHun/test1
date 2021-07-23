package com.ex1.demo.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class AuthKey {
	private int id;
	private int memberId;
	private String authKey;
	private String issueDate;
	private String expireDate;
}
