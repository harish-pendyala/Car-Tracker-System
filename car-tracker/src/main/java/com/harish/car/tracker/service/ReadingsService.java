package com.harish.car.tracker.service;

import java.util.List;

import com.harish.car.tracker.entity.Reading;

public interface ReadingsService {
	
	public List<Reading> findAll();
	
	public List<Reading> findReadings(String vin);
	
	public Reading addReading(Reading reading);

    
}
