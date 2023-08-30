package com.app.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.app.entity.Job;
import com.app.entity.JobProvider;

@Repository
public interface JobRepo extends JpaRepository<Job, Long>{
	
	List<Job> findByAssignedJpId(JobProvider assignedJpId);	
	
	

}
