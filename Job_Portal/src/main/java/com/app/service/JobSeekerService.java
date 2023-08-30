package com.app.service;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.app.dto.InsertJobseekerDto;
import com.app.dto.JSUpdateDto;
import com.app.dto.Signindto;
import com.app.entity.JobSeeker;

public interface JobSeekerService {
	
//	String insertJobSeeker(InsertJobseekerDto dto,MultipartFile resume,MultipartHttpServletRequest request);
	String insertJobSeeker(InsertJobseekerDto dto);

	List<JobSeeker> getAllSeekerList();

	JobSeeker getJobSeekerById(long jsid);
	
	JobSeeker signIn(Signindto dto,HttpServletResponse response,HttpSession session);

	String deleteJsById(long jsId);

	String updateJSByUserName(String userName, JSUpdateDto updateDto);

	JobSeeker getJobSeekerByUserName(String userName);
	
	String sendOtp(String userName);
	
	boolean verifyOtp(String userName,Integer intotp);
	
	String resetPassword(String userName,String newPassword);
}
