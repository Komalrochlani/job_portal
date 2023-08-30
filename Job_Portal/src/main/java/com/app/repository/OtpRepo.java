package com.app.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.app.entity.OTP;

public interface OtpRepo extends JpaRepository<OTP, Long> {
	
	Optional<OTP> findByEmailAndOtp(String email,int otp);

}
