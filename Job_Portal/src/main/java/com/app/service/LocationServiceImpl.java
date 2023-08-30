package com.app.service;

import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.app.dto.LocationDto;
import com.app.entity.Location;
import com.app.repository.LocationRepo;

@Service
@Transactional
public class LocationServiceImpl implements LocationService{

	@Autowired
	private LocationRepo locationRepo;
	
	@Autowired
	private ModelMapper mapper; 
	
	@Override
	public String insertLocationDetails(LocationDto location) {
		
		Location local = mapper.map(location,Location.class);
		try{
			locationRepo.save(local);
		}catch(Exception e) {
			e.printStackTrace();
			return "Failed";
		}
		return "Success";
	}

	@Override
	public List<Location> allLocation() {
		
		return locationRepo.findAll();
	}

	
}
