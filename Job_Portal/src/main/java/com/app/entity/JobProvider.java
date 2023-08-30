package com.app.entity;

import java.util.HashMap;
import java.util.Map;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.validation.constraints.Email;

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
@Table(name = "jobprovider")
public class JobProvider {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)

	private Long jpId;
	@Column(length = 40, nullable = false)
	private String jpName;
	@Column(length = 40, nullable = false,unique = true)
	private String userName;
	@Column(nullable = false)
	private String password;
	@Email
	@Column(unique = true, length=100)
	private String email;
	@Column(length = 300, nullable = false)
	private String address;
	@Column(length = 100)
	private String webSite;
	@Column(length = 40, nullable = false,unique = true)
	private String phoneNo;
	
	@Transient
	@OneToMany(mappedBy = "assignedJpId",cascade = CascadeType.ALL,orphanRemoval = true,fetch = FetchType.EAGER)
	private Map<Long, Job> jobList=new HashMap<Long, Job>();
	
	public JobProvider(String jpName, String userName, String password, @Email String email, String address,
			String webSite, String phoneNo) {
		super();
		this.jpName = jpName;
		this.userName = userName;
		this.password = password;
		this.email = email;
		this.address = address;
		this.webSite = webSite;
		this.phoneNo = phoneNo;
	}
	public JobProvider(Long jpId, String jpName, String userName, String password, @Email String email, String address,
			String webSite, String phoneNo) {
		super();
		this.jpId = jpId;
		this.jpName = jpName;
		this.userName = userName;
		this.password = password;
		this.email = email;
		this.address = address;
		this.webSite = webSite;
		this.phoneNo = phoneNo;
	}
	
	
	public void addJobIntoJobList(Job job)
	{
		jobList.put(job.getJobId(), job);
	}
	
	
	
	

}
