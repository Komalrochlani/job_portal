package com.app.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.app.entity.Application;
import com.app.entity.Job;
import com.app.entity.JobSeeker;
import com.app.entity.JobStatus;
import com.app.repository.ApplicationRepo;
import com.app.repository.JobRepo;
import com.app.repository.JobSeekerRepo;

@Service
@Transactional
public class ApplicationServiceImpl implements ApplicationService {
	
	public ApplicationServiceImpl() {
		System.out.println("ApplicationService started");
	}
	
	@Autowired
	private ApplicationRepo applRepo;
	@Autowired
	private JobRepo jobRepo;
	@Autowired
	private JobSeekerRepo jobSeekerRepo;
	@Autowired
	private JavaMailSender sender;
	
//	@Autowired
//	private EmailService emailService;
	/*
	 * @Override public String insertApplication(ApplicationSubmitdto dto) {
	 * 
	 * try { System.out.println(dto); Application appl=mapper.map(dto,
	 * Application.class); System.out.println(appl); JobSeeker
	 * js=jsService.getJobSeekerById(dto.getJsId()); System.out.println(js); Job
	 * job=jobRepo.findById(dto.getJobId()).get(); System.out.println(job);
	 * appl.setAssignedJobId(job); appl.setAssignedJsId(js);
	 * System.out.println(appl); js.addApplication(appl); job.addApplication(appl);
	 * applRepo.save(appl); }catch (Exception e) { return "Failed"; } return
	 * "Success"; }
	 */
	
	@Override
	public String insertApplication(String userName, long jobId) {
		try {
//			JSONObject jsonObject= new JSONObject(userName);
//			userName = jsonObject.getString("userName");
			System.out.println(userName);
			JobSeeker jobSeeker = jobSeekerRepo.findByUserName(userName).get();
			Application appl=new Application();
			//System.out.println(appl);
			Job job=jobRepo.findById(jobId).get();		
			appl.setAssignedJobId(job);
			appl.setAssignedJsId(jobSeeker);
			appl.setStatus(JobStatus.APPLIED);
			applRepo.save(appl);
			//jobSeeker.addApplication(appl);
			SimpleMailMessage mgs=new SimpleMailMessage();
			mgs.setTo(jobSeeker.getEmail());
			mgs.setSubject("Application to job");
			mgs.setText("Dear " + jobSeeker.getJsFullName() + ",\n\n"
	                + "Your application for the job " + job.getJobTitle() + " has been successfully submitted."
	                + "\n\nBest regards,\nThe Application Team");
			sender.send(mgs);
			
			
		}catch (Exception e) {
			e.printStackTrace();
			return "Failed"+e.getMessage();
		}
		return "Success";
	}

	@Override
	public List<Application> listApplicationsByJob(Long jobId) {
		Job job=jobRepo.findById(jobId).get();
				
		return applRepo.findByAssignedJobId(job);
	}

	@Override
	public List<Application> listJobsByJsId(String userName) {
		JobSeeker js = jobSeekerRepo.findByUserName(userName).get();
		return applRepo.findByAssignedJsId(js);
	}
}
