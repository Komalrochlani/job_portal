package com.app.entity;

import java.io.Serializable;
import java.sql.Blob;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.validation.constraints.Email;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonIgnoreType;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Getter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Setter
@JsonIgnoreProperties
@JsonIgnoreType
@Table(name = "jobseeker")
public class JobSeeker implements Serializable {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long jsId;
	@Column(length = 100)
	private String jsFullName;
	@Column(length = 40, nullable = false, unique = true)
	private String userName;
	@Column( nullable = false)
	private String password;
	@Email
	@Column(unique = true, length = 100)
	private String email;
	@Column(length = 300, nullable = false)
	private String address;
	@Column(length = 40, nullable = false, unique = true)
	private String phoneNo;
	@Enumerated(EnumType.STRING)
	private Gender gender;
	@Column(length = 40)
	private String education;
	@Column(length = 40)
	private String skill1;
	@Column(length = 40)
	private String skill2;
	@Column(length = 40)
	private String skill3;
	private int experience;
	private Blob resume;
	
	@Transient
	@OneToMany(mappedBy = "assignedJsId", cascade = CascadeType.ALL, orphanRemoval = true,fetch = FetchType.EAGER)
	private Map<Long, Application> applicationList=new HashMap<Long, Application>();

	public JobSeeker(String jsFullName, String userName, String password, String email, String address, String phoneNo,
			Gender gender, String education, String skill1, String skill2, String skill3, int experience) {
		super();
		System.out.println("ctr1");
		this.jsFullName = jsFullName;
		this.userName = userName;
		this.password = password;
		this.email = email;
		this.address = address;
		this.phoneNo = phoneNo;
		this.gender = gender;
		this.education = education;
		this.skill1 = skill1;
		this.skill2 = skill2;
		this.skill3 = skill3;
		this.experience = experience;
	}

	public JobSeeker(Long jsId, String jsFullName, String userName, String password, String email, String address,
			String phoneNo, Gender gender, String education, String skill1, String skill2, String skill3,
			int experience) {
		super();
		System.out.println("ctr2");
		this.jsId = jsId;
		this.jsFullName = jsFullName;
		this.userName = userName;
		this.password = password;
		this.email = email;
		this.address = address;
		this.phoneNo = phoneNo;
		this.gender = gender;
		this.education = education;
		this.skill1 = skill1;
		this.skill2 = skill2;
		this.skill3 = skill3;
		this.experience = experience;
	}
	
	public void addApplication(Application app)
	{
		applicationList.put(app.getApplicationId(), app);
	}
}
