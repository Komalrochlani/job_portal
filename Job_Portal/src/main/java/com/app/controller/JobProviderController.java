package com.app.controller;

import java.util.List;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.app.dto.JPRegisterdto;
import com.app.dto.JPUpdateDto;
import com.app.dto.Signindto;
import com.app.entity.JobProvider;
import com.app.service.JobProviderService;

@RestController
@RequestMapping("/jobprovider")
@CrossOrigin(origins = "http://localhost:3000")
public class JobProviderController {
	
	public JobProviderController() {
		System.out.println("JobProviderController started");
	}
	
	@Autowired
	private JobProviderService jpService;
	
	@PostMapping("/registration")
	public ResponseEntity<?> registerJP(@RequestBody JPRegisterdto dto)
	{
		
		return ResponseEntity.status(HttpStatus.OK).body(jpService.registerJP(dto));
	}
	
	@PostMapping("/signin")
	public ResponseEntity<?> JpSignin(@RequestBody Signindto dto,HttpServletResponse response,HttpSession session)
	{
		return ResponseEntity.status(HttpStatus.OK).body(jpService.signIn(dto,response,session));
	}
	
	@GetMapping("/providerlist")
	public List<JobProvider> listJobProvider()
	{
		return jpService.getAllJP();
	}
	
	@GetMapping("/{jpid}")
	public JobProvider getById(@PathVariable Long jpid)
	{
		return jpService.getJPbyID(jpid);
	}
	
	@GetMapping("/getJPDetails/{userName}")
	public JobProvider getByUserName(@PathVariable String userName)
	{
		return jpService.getJPbyUserName(userName);
	}
	
	@PostMapping("/updateJP/{userName}")
	public String updateJP(@PathVariable String userName, @RequestBody JPUpdateDto jpUpdateDto)
	{
		String result = jpService.updateJPDetails(userName,jpUpdateDto);
		return result;
	}
	
	@DeleteMapping("/delete/{jpId}")
	public String deleteJPById(@PathVariable Long jpId)
	{
		return jpService.deleteJP(jpId);
	}
	
	@GetMapping("/forgotpass/{userName}")
	public String forgotPassword(@PathVariable String userName)
	{
		return jpService.sendOtp(userName);
	}
	
	@GetMapping("verify/{userName}/{otp}")
	public String verifyOtp(@PathVariable String userName, @PathVariable String otp) {
		Integer intotp=Integer.parseInt(otp);
        boolean isOtpValid = jpService.verifyOtp(userName, intotp);
        if (isOtpValid) {
            return "OTP verified successfully.";
        } else {
            return "Invalid OTP";
        }
    } 
	
	@PostMapping("/resetpass/{userName}")
    public ResponseEntity<String> resetPassword(@PathVariable String userName, @RequestBody String newPassword) {
        String result = jpService.resetPassword(userName, newPassword);
        return ResponseEntity.ok(result);
    }

}
