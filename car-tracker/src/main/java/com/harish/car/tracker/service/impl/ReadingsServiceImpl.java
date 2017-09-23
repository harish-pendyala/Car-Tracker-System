package com.harish.car.tracker.service.impl;


import java.sql.Timestamp;
import java.time.Instant;
import java.util.Date;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.harish.car.tracker.entity.Alert;
import com.harish.car.tracker.entity.Reading;
import com.harish.car.tracker.entity.Tires;
import com.harish.car.tracker.entity.Vehicle;
import com.harish.car.tracker.exception.NotFoundException;
import com.harish.car.tracker.repository.ReadingsRepository;
import com.harish.car.tracker.repository.TiresRepository;
import com.harish.car.tracker.service.AlertsService;
import com.harish.car.tracker.service.ReadingsService;
import com.harish.car.tracker.service.VehicleService;

@Service
public class ReadingsServiceImpl implements ReadingsService{
	
	private ReadingsRepository repository;
	private TiresRepository tRepository;
	private AlertsService alertsService;
	private VehicleService vehicleService;
	
	public ReadingsServiceImpl(ReadingsRepository repository,AlertsService alertsService, VehicleService vehicleService,TiresRepository tRepository) {
		this.repository = repository;
		this.tRepository = tRepository;
		this.alertsService = alertsService;
		this.vehicleService = vehicleService;
	}

	@Override
	@Transactional(readOnly = true)
	public List<Reading> findAll() {
		return repository.findAll();
	}

	@Override
	@Transactional(readOnly = true)
	public List<Reading> findReadings(String vin) {
		return repository.findByVin(vin);
	}


	@Override
	public Reading addReading(Reading reading) {
		Tires t = tRepository.save(reading.getTires());
		Reading r = repository.save(reading);
		Vehicle v= vehicleService.findOne(r.getVin());
		String priority;
		String type;
		String description;
        if(v != null) {
        	System.out.println("Creating Alert ");
            if (reading.getEngineRpm() > v.getRedlineRpm()) {
                type = "Engine RPM";
                priority = "HIGH";
                description = "Engine RPM exceeds Red Line RPM.";
                System.out.println("Creating Alert of RPM ");
                createAlert(reading,v,type,priority,description);
            }
            if (reading.getFuelVolume() < (v.getMaxFuelVolume()*0.1)) {
                type = "Fuel Volume";
                priority = "MEDIUM";
                description = "Fuel Volume is below Minimum Level.";
                System.out.println("Creating Alert of Fuel ");
                createAlert(reading,v,type,priority,description);
            }
            if (checkTirePressureLow(reading.getTires())) {
                type = "Tire Pressure Low";
                priority = "LOW";
                description = "Tire Pressure is below Minimum Level.";
                System.out.println("Creating Alert of Low Tire ");
                createAlert(reading,v,type,priority,description);
            }
            if (checkTirePressureHigh(reading.getTires())) {
                type = "Tire Pressure High";
                priority = "LOW";
                description = "Tire Pressure is Above Maximum Level.";
                System.out.println("Creating Alert of high Tire ");
                createAlert(reading,v,type,priority,description);
            }
            if (reading.isEngineCoolantLow() || reading.isCheckEngineLightOn()) {
                type = "Engine Issue";
                priority = "LOW";
                description = "Either Engine Coolant is Low or Engine Light is ON.";
                createAlert(reading,v,type,priority,description);
            }
        }
		
		return r;
		
	}
	
	private void createAlert(Reading reading, Vehicle v, String type, String priority, String description) {
		Alert a = new Alert();
		a.setAlert_type(type);
		a.setDescription(description);
		a.setPriority(priority);
		a.setReading(reading);
		a.setVehicle(v);
		a.setVin(v.getVin());
		Timestamp timestamp = new Timestamp(System.currentTimeMillis());
		Instant instant = timestamp.toInstant();
		a.setTimestamp(instant.toString());
		alertsService.createAlert(a);
		
	}

	public boolean checkTirePressureLow(Tires tires) {
        float value = 32.0f;
        if(tires.getFrontLeft() < value || tires.getFrontRight() < value || tires.getRearLeft() < value || tires.getRearRight() < value){
        	return true;        	
        }
        return false;
    }
	public boolean checkTirePressureHigh(Tires tires) {
        float value = 36.0f;
        if(tires.getFrontLeft() > value || tires.getFrontRight() > value || tires.getRearLeft() > value || tires.getRearRight() > value){
        	return true;        	
        }
        return false;
    }
}
