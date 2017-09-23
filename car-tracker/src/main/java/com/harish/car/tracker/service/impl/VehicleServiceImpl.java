package com.harish.car.tracker.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.harish.car.tracker.entity.Vehicle;
import com.harish.car.tracker.exception.NotFoundException;
import com.harish.car.tracker.repository.VehicleRepository;
import com.harish.car.tracker.service.VehicleService;

@Service
public class VehicleServiceImpl implements VehicleService{
	
	private VehicleRepository repository;
	public VehicleServiceImpl(VehicleRepository repository) {
		this.repository = repository;
	}

	@Override
	@Transactional(readOnly = true)
	public List<Vehicle> findAll() {
		return repository.findAll();
	}

	@Override
	@Transactional(readOnly = true)
	public Vehicle findOne(String vin) {
		Vehicle existing = repository.findOne(vin);
		if (existing == null){
			throw new NotFoundException("Vehicle with vin "+ vin + "does not exist");
		}
		return existing;
	}

    public void loadVehicles(Vehicle[] vehicles) {
        for(Vehicle vehicle : vehicles) {
        	
        	repository.save(vehicle);

        }
    }
    

}
