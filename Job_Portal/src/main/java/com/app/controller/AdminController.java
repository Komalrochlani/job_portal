package com.app.controller;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

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

import com.app.dto.AdminRegisterDto;
import com.app.dto.Signindto;
import com.app.entity.Admin;
import com.app.service.AdminService;

@RestController
@RequestMapping("/admin")
@CrossOrigin(origins = "http://localhost:3000")
public class AdminController {
	
	public AdminController() {
		System.out.println("Admin Controller Started");
	}
	
	@Autowired
	private AdminService adminService;
	
	
	@PostMapping("/registration")
	public ResponseEntity<?> registerAdmin(@RequestBody AdminRegisterDto admin)
	{
		return ResponseEntity.status(HttpStatus.CREATED).body(adminService.insertAdmin(admin));
	}
	
	@PostMapping("/signin")
	public ResponseEntity<?> signIn(@RequestBody Signindto dto,HttpServletResponse response,HttpSession session)
	{
		return ResponseEntity.ok(adminService.signIn(dto,response,session));
	}
	
//	@GetMapping("/forgotpass/{userName}")
//	public String forgotPassword(@PathVariable String userName)
//	{
//		return adminService.sendOtp(userName);
//	}
//	
//	@GetMapping("verify/{userName}/{otp}")
//	public String verifyOtp(@PathVariable String userName, @PathVariable String otp) {
//		Integer intotp=Integer.parseInt(otp);
//        boolean isOtpValid = adminService.verifyOtp(userName, intotp);
//        if (isOtpValid) {
//            return "OTP verified successfully.";
//        } else {
//            return "Invalid OTP";
//        }
//    } 
//	
//	@PostMapping("/resetpass/{userName}")
//    public ResponseEntity<String> resetPassword(@PathVariable String userName, @RequestBody String newPassword) {
//        String result = adminService.resetPassword(userName, newPassword);
//        return ResponseEntity.ok(result);
//    }

}
