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
@Getter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Setter
@Table(name = "jobCategory")
public class JobCategory {
	
	@Id 
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long jcId;
	@Column(length = 50,unique = true)
	private String jcName;
}
