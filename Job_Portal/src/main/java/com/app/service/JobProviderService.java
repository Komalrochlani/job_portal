package com.app.service;

import java.util.List;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.app.dto.JPRegisterdto;
import com.app.dto.JPUpdateDto;
import com.app.dto.Signindto;
import com.app.entity.JobProvider;

public interface JobProviderService {
	
	String registerJP(JPRegisterdto dto);
	
	List<JobProvider> getAllJP();
	
	JobProvider getJPbyID(Long jpid);
	
	JobProvider signIn(Signindto dto,HttpServletResponse response,HttpSession session);

	String updateJPDetails(String userName, JPUpdateDto jpUpdateDto);

	JobProvider getJPbyUserName(String userName);
	
	String deleteJP(Long jpId);

	String sendOtp(String userName);

	boolean verifyOtp(String userName, Integer intotp);

	String resetPassword(String userName, String newPassword);

}
