package com.harish.car.tracker.service;

import java.util.List;

import com.harish.car.tracker.entity.Vehicle;

public interface VehicleService {
	
	public List<Vehicle> findAll();
	
	public Vehicle findOne(String vin);
	
	public void loadVehicles(Vehicle[] vehicles);

    
}
