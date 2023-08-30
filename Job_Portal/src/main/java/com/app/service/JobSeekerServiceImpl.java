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
import com.app.dto.InsertJobseekerDto;
import com.app.dto.JSUpdateDto;
import com.app.dto.Signindto;
import com.app.entity.JobSeeker;
import com.app.entity.OTP;
import com.app.repository.JobSeekerRepo;
import com.app.repository.OtpRepo;
import com.app.util.JwtUtil;
import com.app.util.SaveCookie;

@Service
@Transactional
public class JobSeekerServiceImpl implements JobSeekerService {

	public JobSeekerServiceImpl() {
		System.out.println("JobSeeker Service Started");
	}

	@Autowired
	private JobSeekerRepo jsRepo;

	@Autowired
	private ModelMapper mapper;
	
	@Autowired
	private JwtUtil jwtUtil;
	
	@Autowired
	private JavaMailSender sender;
	@Autowired
	private OtpRepo otpRepo;

//	@Override
//	public String insertJobSeeker(InsertJobseekerDto dto,MultipartFile resume,MultipartHttpServletRequest request) {
//		
//		System.out.println(dto);
//
//		JobSeeker js = mapper.map(dto, JobSeeker.class);
//
//		try {
//			System.out.println(js);
//			jsRepo.save(js);
//			 if (resume != null && !resume.isEmpty()) {
//				 Blob resumeBlob = new SerialBlob(resume.getBytes());
//
//		            // Set the resume field as a Blob in the entity
//		            js.setResume(resumeBlob);
//		            
//		        }
//			 jsRepo.save(js); // Update the entity to associate the resume
//
//		} catch (Exception e) {
//			return "Fail";
//		}
//		return "Success";
//	}
	
	

	@Override
	public String insertJobSeeker(InsertJobseekerDto dto) {
		BCryptPasswordEncoder bcrypt=new BCryptPasswordEncoder();
		System.out.println(dto);
		JobSeeker js = mapper.map(dto, JobSeeker.class);
		String encryptedPassword=bcrypt.encode(js.getPassword());
		System.out.println(encryptedPassword);
		js.setPassword(encryptedPassword);
		try {
			System.out.println(js);
			jsRepo.save(js);
		} catch (Exception e) {
			return "Fail";
		}
		return "Success";
	}

	@Override
	public List<JobSeeker> getAllSeekerList() {

		return jsRepo.findAll();
	}

	@Override
	public JobSeeker getJobSeekerById(long jsid) {

		return jsRepo.findById(jsid).orElseThrow(() -> new ResourceNotFoundException("Id not found"));
	}
	
	@Override
	public JobSeeker getJobSeekerByUserName(String userName) {

		return jsRepo.findByUserName(userName).orElseThrow(() -> new ResourceNotFoundException("Id not found"));
	}

	@Override
	public String deleteJsById(long jsId) {
		String msg = "Job Seeker Id does not exist";
		if (jsRepo.existsById(jsId)) {
			jsRepo.deleteById(jsId);
			msg = "Job Seeker deleted";
		}
		return msg;
	}

	@Override
//	public boolean signIn(Signindto dto, HttpServletRequest request) {
//		try {
//			JobSeeker js = jsRepo.findByUserNameAndPassword(dto.getUserName(), dto.getPassword())
//					.orElseThrow(() -> new ResourceNotFoundException("Invalid credetials"));
//			// System.out.println(js);
//			HttpSession session = request.getSession();
//
//			session.setAttribute("jsID", js.getJsId());
//			session.setAttribute("jsName", js.getUserName());
//		} catch (Exception e) {
//			return false;

	public JobSeeker signIn(Signindto dto,HttpServletResponse response,HttpSession session) {
		try {
			JobSeeker js=jsRepo.findByUserNameAndPassword(dto.getUserName(), dto.getPassword()).orElseThrow(()->new ResourceNotFoundException("Invalid credetials"));
			//System.out.println(js);
			BCryptPasswordEncoder bcrypt=new BCryptPasswordEncoder();
			if(bcrypt.matches(dto.getPassword(), js.getPassword()))
			{
				final String jwt=jwtUtil.generateToken(js.getUserName());
				session.setAttribute("js", js);
				SaveCookie.sendToken(jwt, response);
				
			}
			return js;
			
		}catch (Exception e) {
			return null;

		}
		
	}

	@Override
	public String updateJSByUserName(String userName, JSUpdateDto updateDto) {
		Optional<JobSeeker> existingId = jsRepo.findByUserName(userName);
		if (existingId.isPresent()) {
			JobSeeker existingJobSeeker = existingId.get();
			existingJobSeeker.setJsFullName(updateDto.getJsFullName());
			existingJobSeeker.setEmail(updateDto.getEmail());
			existingJobSeeker.setAddress(updateDto.getAddress());
			existingJobSeeker.setPhoneNo(updateDto.getPhoneNo());
			existingJobSeeker.setGender(updateDto.getGender());
			existingJobSeeker.setEducation(updateDto.getEducation());
			existingJobSeeker.setSkill1(updateDto.getSkill1());
			existingJobSeeker.setSkill2(updateDto.getSkill2());
			existingJobSeeker.setSkill3(updateDto.getSkill3());
			existingJobSeeker.setExperience(updateDto.getExperience());
			jsRepo.save(existingJobSeeker);
			return "Updated Successfully";
		} else {
			return "Id Does Not Exist";
		}

	}

	@Override
	public String sendOtp(String userName) {
		JobSeeker js=jsRepo.findByUserName(userName).get();
		Random random=new Random();
		Integer otp=random.nextInt(99999);
		OTP persistentOtp=new OTP(js.getEmail(), otp);
		otpRepo.save(persistentOtp);
		SimpleMailMessage mgs=new SimpleMailMessage();
		mgs.setTo(js.getEmail());
		mgs.setSubject("Otp for password reset");
		mgs.setText("Otp for password reset is "+otp);
		sender.send(mgs);
		return "otp sent";
	}

	@Override
	public boolean verifyOtp(String userName, Integer otp) {
	    Optional<JobSeeker> jobSeekerOptional = jsRepo.findByUserName(userName);
	    if (jobSeekerOptional.isPresent()) {
	        JobSeeker js = jobSeekerOptional.get();
	        
	        Optional<OTP> otpOptional = otpRepo.findByEmailAndOtp(js.getEmail(), otp);
	        if (otpOptional.isPresent()) {
	            OTP persistentOtp = otpOptional.get();
	            System.out.println(persistentOtp);
	            return persistentOtp.getOtp() == otp;
	        }
	    }
	    return false;
	}


	@Override
	public String resetPassword(String userName, String newPassword) {
		JobSeeker js=jsRepo.findByUserName(userName).get();
		 if (js != null) {
	            // Update password logic
	            js.setPassword(newPassword);
	            jsRepo.save(js);
	            return "Password reset successful.";
	        } else {
	            return "User not found.";
	        }
	}
}
