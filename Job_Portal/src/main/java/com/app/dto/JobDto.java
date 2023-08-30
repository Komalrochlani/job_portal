package com.app.dto;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.app.entity.JobCategory;
import com.app.entity.JobProvider;
import com.app.entity.Location;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class JobDto {

	private long assignedJpId;
	private long assignedJcId;
	private long assignedLocationId;
	private String jobTitle;
	private String role;
	private String keySkills;
	private double salary;
	private String description;
	private int vacancies;
	private LocalDate applicationDeadline;
}
