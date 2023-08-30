package com.app.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.app.dto.JcInsertdto;
import com.app.entity.JobCategory;
import com.app.service.JobCategoryService;

@RestController
@RequestMapping("/jobcategory")
@CrossOrigin(origins = "http://localhost:3000")
public class JobCategoryController {

	@Autowired
	private JobCategoryService jobCategoryService;
	
	@PostMapping("/insertjc")
	public ResponseEntity<?> insertJobCategory(@RequestBody JcInsertdto jobCategory)
	{
		String result = jobCategoryService.insertJobCategoryDetails(jobCategory);
		return ResponseEntity.status(HttpStatus.CREATED).body(result);
	}
	
	@GetMapping("/list")
	public List<JobCategory> getAllJobCategories()
	{
		return jobCategoryService.allCategories();
	}
}
