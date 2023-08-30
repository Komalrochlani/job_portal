package com.app.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.app.entity.Application;
import com.app.entity.Job;
import com.app.service.ApplicationService;

@RestController
@RequestMapping("/application")
@CrossOrigin(origins = "http://localhost:3000")
public class ApplicationController {
	
	public ApplicationController() {
		System.out.println("Application Controller Started");
	}
	
	@Autowired
	private ApplicationService applService;
	
	/*
	 * @PostMapping("/apply") public ResponseEntity<?> jobApplication(@RequestBody
	 * ApplicationSubmitdto dto) {
	 * 
	 * return
	 * ResponseEntity.status(HttpStatus.CREATED).body(applService.insertApplication(
	 * dto)); }
	 */
	
	@PostMapping("/apply/{jobId}")
	public ResponseEntity<?> jobApplication(@PathVariable long jobId,
			@RequestBody Map<String, String> requestData)
	{
		String userName = requestData.get("userName");
		return ResponseEntity.status(HttpStatus.CREATED)
				.body(applService.insertApplication(userName, jobId));
	}
	
	@GetMapping("/job/{jobId}")
	public List<Application> getAllApplicationsForAjob(@PathVariable Long jobId)
	{
		return applService.listApplicationsByJob(jobId);
	}
	
	@GetMapping("/jobs/jobseeker/{userName}")
	public List<Application> getAllJobsByJs(@PathVariable String userName)
	{
		return applService.listJobsByJsId(userName);
	}
}
