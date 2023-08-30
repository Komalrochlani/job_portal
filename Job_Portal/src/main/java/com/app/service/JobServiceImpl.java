package com.app.service;

import java.util.List;
import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.app.dto.JobDto;
import com.app.entity.Application;
import com.app.entity.Job;
import com.app.entity.JobCategory;
import com.app.entity.JobProvider;
import com.app.entity.JobSeeker;
import com.app.entity.Location;
import com.app.repository.ApplicationRepo;
import com.app.repository.JobCategoryRepo;
import com.app.repository.JobProviderRepo;
import com.app.repository.JobRepo;
import com.app.repository.JobSeekerRepo;
import com.app.repository.LocationRepo;

@Service
@Transactional
public class JobServiceImpl implements JobService {

	@Autowired
	private JobRepo jobRepo;
	@Autowired
	private JobProviderRepo jobProviderRepo;
	@Autowired
	private LocationRepo locationRepo;
	@Autowired
	private JobCategoryRepo jobCategoryRepo;
	@Autowired
	private JobSeekerRepo jobSeekerRepo;
	@Autowired
	private JobProviderService jpService;
	@Autowired
	private ApplicationRepo applicationRepo;
	@Autowired
	private ModelMapper mapper;

	@Override
	public String insertJob(JobDto dto) {
		try {
			Job job = mapper.map(dto, Job.class);

			JobProvider jp = jpService.getJPbyID(dto.getAssignedJpId());

			job.setAssignedJpId(jp);
//			job.getAssignedJcId(jobCategoryRepo.findById(dto.getAssignedJcId()));
//			Optional<JobProvider> jobProvider = jobProviderRepo.findById(dto.getJpId());			
//			job.setJpId(jobProvider.get());
			Optional<JobCategory> jobCategory = jobCategoryRepo.findById(dto.getAssignedJcId());
			job.setAssignedJcId(jobCategory.get());
			Optional<Location> location = locationRepo.findById(dto.getAssignedLocationId());
			job.setAssignedLocationId(location.get());
			jobRepo.save(job);
		} catch (Exception e) {
			e.printStackTrace();
			return "Failed";
		}
		return "Success";
	}

	@Override
	public List<Job> listAllJobs(String userName) {
		List<Job> listJob = jobRepo.findAll();
		JobSeeker jobSeeker = jobSeekerRepo.findByUserName(userName).get();
		for (Job job : listJob) {
			for (Application app : applicationRepo.findByAssignedJobId(job)) {
				if (app.getAssignedJsId().getJsId().equals(jobSeeker.getJsId())) {
					job.setIsApplied(true);
					break;
				}
			}
		}
		return listJob;
	}

	@Override
	public List<Job> listJobs() {
		
		return jobRepo.findAll();
	}

	@Override
	public List<Job> listJobsByJpId(String UserName) {
		
			JobProvider jp=jobProviderRepo.findByUserName(UserName).get();
		return jobRepo.findByAssignedJpId(jp);
	}

	

}
