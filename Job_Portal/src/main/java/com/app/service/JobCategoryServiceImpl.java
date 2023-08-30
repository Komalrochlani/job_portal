package com.app.service;

import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.app.dto.JcInsertdto;
import com.app.entity.JobCategory;
import com.app.repository.JobCategoryRepo;

@Service
@Transactional
public class JobCategoryServiceImpl implements JobCategoryService {

	@Autowired
	private JobCategoryRepo jobCategoryRepo;
	
	@Autowired
	private ModelMapper mapper;
	@Override
	public String insertJobCategoryDetails(JcInsertdto dto) {
		
		try{
			jobCategoryRepo.save(mapper.map(dto, JobCategory.class));
		}catch(Exception e) {
			e.printStackTrace();
			return "Failed";
		}
		return "Success";
	}

	@Override
	public List<JobCategory> allCategories() {
		
		return jobCategoryRepo.findAll();
	}

}
