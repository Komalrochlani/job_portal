package com.app.entity;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.hibernate.annotations.CreationTimestamp;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name="application")
@Getter
@ToString
@NoArgsConstructor
@Setter
@AllArgsConstructor
public class Application {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long applicationId;
	
	@Enumerated(EnumType.STRING)
	@Column(length=20)
	private JobStatus status;
	
	@ManyToOne
	@JoinColumn(name = "jobId" , nullable = false)
	private Job assignedJobId;
	
	@OneToOne
	@JoinColumn(name = "jsId" , nullable = false)
	private JobSeeker assignedJsId;
	
	@CreationTimestamp
	private LocalDateTime applicationDate;
	
	
	

}
