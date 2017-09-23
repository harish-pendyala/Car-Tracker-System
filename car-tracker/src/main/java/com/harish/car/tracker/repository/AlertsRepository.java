package com.harish.car.tracker.repository;

import java.util.List;

import org.springframework.data.repository.Repository;

import com.harish.car.tracker.entity.Alert;

public interface AlertsRepository extends Repository<Alert, String> {
	public List<Alert> findAll();
	public List<Alert> findByVin(String vin);
	public void save(Alert alert);


}
