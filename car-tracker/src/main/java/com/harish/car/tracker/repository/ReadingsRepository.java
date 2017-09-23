package com.harish.car.tracker.repository;

import java.util.List;

import org.springframework.data.repository.Repository;

import com.harish.car.tracker.entity.Reading;

public interface ReadingsRepository extends Repository<Reading, String>{
	public List<Reading> findAll();
	public List<Reading> findByVin(String vin);
	public Reading save(Reading reading);


}
