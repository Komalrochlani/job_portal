package com.app.entity;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Transient;

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
public class Job {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long jobId;
	
	@Column(length = 50)
	private String jobTitle;
	
	@Column(length = 50)
	private String role;
	
	@Column(length = 50,nullable = false)
	private String keySkills;
	
	@Column(nullable = false)
	private double salary;
	
	@Column(length = 500)
	private String description;
	
	@Column(nullable = false)
	private int vacancies;
	
	@Column(nullable = false)
	private LocalDate applicationDeadline;
	
	@ManyToOne
	@JoinColumn(name = "jobProviderId", nullable = false)
	private JobProvider assignedJpId;
	
	@ManyToOne
	@JoinColumn(name = "jobCategoryId", nullable = false)
	private JobCategory assignedJcId;
	
	@ManyToOne
	@JoinColumn(name = "locationId", nullable = false)
	private Location assignedLocationId;
	
	@Transient
	@OneToMany(mappedBy = "assignedJobId",cascade = CascadeType.ALL,orphanRemoval = true,fetch = FetchType.EAGER)
	private Map<Long,Application> applicationList=new HashMap<Long, Application>();
	
	@Transient
	private Boolean isApplied = false;
	
	public void addApplication(Application appl)
	{
		applicationList.put(appl.getApplicationId(), appl);
	}
	
	
    public List<Application> getApplications() {
        return new ArrayList<>(applicationList.values());
    }
	

}
