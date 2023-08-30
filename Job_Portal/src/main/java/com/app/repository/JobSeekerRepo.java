package com.app.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.app.entity.JobSeeker;

@Repository
public interface JobSeekerRepo extends JpaRepository<JobSeeker, Long> {
	
	Optional<JobSeeker> findByUserNameAndPassword(String userName,String password);
	Optional<JobSeeker> findByUserName(String userName);
}
