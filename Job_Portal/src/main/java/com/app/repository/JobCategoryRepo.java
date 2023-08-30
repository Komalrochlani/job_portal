package com.app.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.app.entity.JobCategory;

@Repository
public interface JobCategoryRepo extends JpaRepository<JobCategory, Long> {

}
