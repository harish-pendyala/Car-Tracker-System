package com.harish.car.tracker.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.harish.car.tracker.entity.Alert;
import com.harish.car.tracker.exception.NotFoundException;
import com.harish.car.tracker.repository.AlertsRepository;
import com.harish.car.tracker.service.AlertsService;

@Service
public class AlertsServiceImpl implements AlertsService{
	
	private AlertsRepository repository;
	public AlertsServiceImpl(AlertsRepository repository) {
		this.repository = repository;
	}

	@Override
	@Transactional(readOnly = true)
	public List<Alert> findAll() {
		return repository.findAll();
	}

	@Override
	@Transactional(readOnly = true)
	public List<Alert> findAlerts(String vin) {
		return repository.findByVin(vin);
	}


	@Override
	public void createAlert(Alert alert) {
		repository.save(alert);
		
	}

}
