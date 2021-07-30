package com.ex1.demo.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class checkLike {
	private int memberId;
	private String typeCode;
	private int typeId;
	private boolean checkLike;
	private boolean checkDislike;
	private String updateDateLike;
}
