package com.app.service;

import java.util.List;

import com.app.dto.JobDto;
import com.app.entity.Job;

public interface JobService {

	String insertJob(JobDto dto);
	
	List<Job> listAllJobs(String userName);
	
	List<Job> listJobs();
	
	List<Job> listJobsByJpId(String UserName);

	

	
}
