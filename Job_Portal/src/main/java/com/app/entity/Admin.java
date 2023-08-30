package com.app.entity;



import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name="admin")
@Getter
@ToString
@NoArgsConstructor
@Setter
@AllArgsConstructor
public class 
Admin {


	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long adminId;
	@Column(length = 50, nullable = false, unique = true)
	private String adminUserName;
	@Column( nullable = false)
	private String adminPassword;

}
