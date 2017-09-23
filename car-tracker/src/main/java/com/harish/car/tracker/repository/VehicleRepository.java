package com.harish.car.tracker.repository;

import java.util.List;

import org.springframework.data.repository.Repository;

import com.harish.car.tracker.entity.Vehicle;

public interface VehicleRepository extends Repository<Vehicle, String>{
	public List<Vehicle> findAll();
	public Vehicle findOne(String id);
	public void save(Vehicle vehicle);


}
