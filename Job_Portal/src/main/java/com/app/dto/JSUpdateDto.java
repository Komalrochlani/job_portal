package com.app.dto;

import com.app.entity.Gender;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class JSUpdateDto {
	
	private String jsFullName;

	private String email;

	private String address;

	private String phoneNo;

	private Gender gender;

	private String education;

	private String skill1;

	private String skill2;

	private String skill3;
	private int experience;
}
