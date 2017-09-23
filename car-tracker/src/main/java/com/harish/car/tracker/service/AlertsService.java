package com.harish.car.tracker.service;

import java.util.List;

import com.harish.car.tracker.entity.Alert;

public interface AlertsService {
	
	public List<Alert> findAll();
	
	public List<Alert> findAlerts(String vin);
	
	public void createAlert(Alert alert);

    
}
