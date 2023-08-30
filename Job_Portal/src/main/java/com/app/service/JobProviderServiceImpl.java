package com.app.service;

import java.util.List;
import java.util.Optional;
import java.util.Random;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.app.customexception.ResourceNotFoundException;
import com.app.dto.JPRegisterdto;
import com.app.dto.JPUpdateDto;
import com.app.dto.Signindto;
import com.app.entity.JobProvider;
import com.app.entity.JobSeeker;
import com.app.entity.OTP;
import com.app.repository.JobProviderRepo;
import com.app.repository.OtpRepo;
import com.app.util.JwtUtil;
import com.app.util.SaveCookie;

@Service
@Transactional
public class JobProviderServiceImpl implements JobProviderService {
	
	public JobProviderServiceImpl() {
		System.out.println("JobProviderService Started");
	}
	
	@Autowired
	private JobProviderRepo jpRepo;
	@Autowired
	private ModelMapper mapper;
	
	@Autowired
	private JwtUtil jwtUtil;
	
	@Autowired
	private OtpRepo otpRepo;
	
	@Autowired
	private JavaMailSender sender;
	
	@Override
	public String registerJP(JPRegisterdto dto) {
		BCryptPasswordEncoder bcrypt=new BCryptPasswordEncoder();
		JobProvider jp=mapper.map(dto, JobProvider.class);
		String encryptedPassword=bcrypt.encode(jp.getPassword());
		System.out.println(encryptedPassword);
		jp.setPassword(encryptedPassword);
		System.out.println(jp.getPassword());
		try
		{
			jpRepo.save(jp);
		}catch (Exception e) {
			return "Failed";
		}
		return "Success";
	}

	@Override
	public List<JobProvider> getAllJP() {
		
		return jpRepo.findAll();
	}

	@Override
	public JobProvider getJPbyID(Long jpid) {
		
		return jpRepo.findById(jpid).orElseThrow(()-> new ResourceNotFoundException("Cannot find id"));
	}
	
	@Override
	public JobProvider getJPbyUserName(String userName) {
		
		return jpRepo.findByUserName(userName).orElseThrow(()-> new ResourceNotFoundException("Cannot find userName"));
	}

	@Override
	public JobProvider signIn(Signindto dto,HttpServletResponse response,HttpSession session) {
		try {
		
		JobProvider jp=jpRepo.findByUserNameAndPassword(dto.getUserName(), dto.getPassword()).orElseThrow(()->new ResourceNotFoundException("invalid credentials"));
		BCryptPasswordEncoder bcrypt=new BCryptPasswordEncoder();
		if(bcrypt.matches(dto.getPassword(), jp.getPassword()))
		{
			final String jwt=jwtUtil.generateToken(jp.getUserName());
			session.setAttribute("jp", jp);
			SaveCookie.sendToken(jwt, response);
		}
		return jp;
		}catch (Exception e) {
			return null;
		}
		
	}

	@Override
	public String updateJPDetails(String userName, JPUpdateDto jpUpdateDto) {
		Optional<JobProvider> existingName = jpRepo.findByUserName(userName);
		if(existingName.isPresent())
		{
			JobProvider existingJP = existingName.get();
			existingJP.setJpName(jpUpdateDto.getJpName());
			existingJP.setEmail(jpUpdateDto.getEmail());
			existingJP.setAddress(jpUpdateDto.getAddress());
			existingJP.setPhoneNo(jpUpdateDto.getPhoneNo());
			existingJP.setWebSite(jpUpdateDto.getWebSite());
			jpRepo.save(existingJP);
			return "Updated Successfully";
		}else {
			return "Name Does Not Exist";
		}
		
	}

	@Override
	public String deleteJP(Long jpId) {
		String msg = "Job Provider Id does not exist";
		if(jpRepo.existsById(jpId)) {
			jpRepo.deleteById(jpId);
			msg="Job Provider Deleted";
		}
		return msg;
	}

	@Override
	public String sendOtp(String userName) {
		JobProvider jp=jpRepo.findByUserName(userName).get();
		Random random=new Random();
		Integer otp=random.nextInt(99999);
		OTP persistentOtp=new OTP(jp.getEmail(), otp);
		otpRepo.save(persistentOtp);
		SimpleMailMessage mgs=new SimpleMailMessage();
		mgs.setTo(jp.getEmail());
		mgs.setSubject("Otp for password reset");
		mgs.setText("Otp for password reset is "+otp);
		sender.send(mgs);
		return "otp sent";
	}

	@Override
	public boolean verifyOtp(String userName, Integer intotp) {
		Optional<JobProvider> jobProviderOptional = jpRepo.findByUserName(userName);
	    if (jobProviderOptional.isPresent()) {
	        JobProvider jp = jobProviderOptional.get();
	        
	        Optional<OTP> otpOptional = otpRepo.findByEmailAndOtp(jp.getEmail(), intotp);
	        if (otpOptional.isPresent()) {
	            OTP persistentOtp = otpOptional.get();
	            System.out.println(persistentOtp);
	            return persistentOtp.getOtp() == intotp;
	        }
	    }
	    return false;
	}

	@Override
	public String resetPassword(String userName, String newPassword) {
		JobProvider jp=jpRepo.findByUserName(userName).get();
		 if (jp != null) {
	            // Update password logic
			 jp.setPassword(newPassword);
			 jpRepo.save(jp);
	            return "Password reset successful.";
	        } else {
	            return "User not found.";
	        }
	}

	
	

}
