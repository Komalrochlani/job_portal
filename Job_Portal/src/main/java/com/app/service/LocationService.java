package com.app.service;

import java.util.List;

import com.app.dto.LocationDto;
import com.app.entity.Location;

public interface LocationService {

	String insertLocationDetails(LocationDto location);

	List<Location> allLocation();

	
}
