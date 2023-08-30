package com.app.dto;

import com.app.entity.Gender;

import lombok.Getter;
import lombok.Setter;

public class ListAllJobSeekerDto {

@Getter
@Setter
private String jsFullName;
	
	private String userName;
	private String password;
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
