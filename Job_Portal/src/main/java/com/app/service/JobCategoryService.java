package com.app.service;

import java.util.List;

import com.app.dto.JcInsertdto;
import com.app.entity.JobCategory;

public interface JobCategoryService {

	String insertJobCategoryDetails(JcInsertdto dto);
	
	List<JobCategory> allCategories();

	
}
