package com.app.service;

import java.util.Random;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.app.dto.AdminRegisterDto;
import com.app.dto.Signindto;
import com.app.entity.Admin;
import com.app.entity.OTP;
import com.app.repository.AdminRepo;
import com.app.repository.OtpRepo;
import com.app.util.JwtUtil;
import com.app.util.SaveCookie;

@Service
@Transactional
public class AdminServiceImpl implements AdminService {

	public AdminServiceImpl() {
		System.out.println("AdminService started");
	}
	
	@Autowired
	private AdminRepo adminRepo;
	@Autowired
	private ModelMapper mapper;
	@Autowired
	private JwtUtil jwtUtil;
	
	@Autowired
	private OtpRepo otpRepo;

	@Override
	public String insertAdmin(AdminRegisterDto admindto) {
		try {
			BCryptPasswordEncoder bcrypt=new BCryptPasswordEncoder();
			Admin admin=mapper.map(admindto, Admin.class);
			String encryptedPassword=bcrypt.encode(admin.getAdminPassword());
			admin.setAdminPassword(encryptedPassword);
			adminRepo.save(admin);
		}catch (Exception e) {
			return "Failed";
		}
		return "Success";
	}

	@Override
	public Admin signIn(Signindto dto,HttpServletResponse response,HttpSession session) {
		
		try {
			BCryptPasswordEncoder bcrypt=new BCryptPasswordEncoder();
			
			Admin admin=adminRepo.findByAdminUserNameAndAdminPassword(dto.getUserName(), dto.getPassword()).get();
			if(bcrypt.matches(dto.getPassword(), admin.getAdminPassword()))
			{
				final String jwt=jwtUtil.generateToken(admin.getAdminUserName());
				session.setAttribute("admin", admin);
				SaveCookie.sendToken(jwt, response);
			}
			return admin;
		}catch (Exception e) {
			return null;
		}
		
		
	}

//	@Override
//	public String sendOtp(String userName) {
//		Admin admin=adminRepo.findByAdminUserName(userName).get();
//		Random random=new Random();
//		Integer otp=random.nextInt(99999);
//		OTP persistentOtp=new OTP(admin.getAdminUserName(), otp);
//		otpRepo.save(persistentOtp);
//		SimpleMailMessage mgs=new SimpleMailMessage();
//		mgs.setTo(js.getEmail());
//		mgs.setSubject("Otp for password reset");
//		mgs.setText("Otp for password reset is "+otp);
//		sender.send(mgs);
//		return "otp sent";
//		return null;
//	}
//
//	@Override
//	public boolean verifyOtp(String userName, Integer intotp) {
//		// TODO Auto-generated method stub
//		return false;
//	}
//
//	@Override
//	public String resetPassword(String userName, String newPassword) {
//		// TODO Auto-generated method stub
//		return null;
//	}
	
}
