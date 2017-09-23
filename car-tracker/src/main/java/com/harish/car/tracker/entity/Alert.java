package com.harish.car.tracker.entity;


import java.sql.Timestamp;
import java.time.Instant;
import java.util.UUID;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;

@Entity
public class Alert {
	
	@Id
	private String id;
	private String vin;
	private String priority;
	private String alert_type;
	private String description;
	private String timestamp;
	
	@ManyToOne
	private Reading reading;
	@ManyToOne
	private Vehicle vehicle;
	
	public Alert(){
		this.id = UUID.randomUUID().toString();
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getVin() {
		return vin;
	}
	public void setVin(String vin) {
		this.vin = vin;
	}
	public String getPriority() {
		return priority;
	}
	public void setPriority(String priority) {
		this.priority = priority;
	}
	public String getAlert_type() {
		return alert_type;
	}
	public void setAlert_type(String alert_type) {
		this.alert_type = alert_type;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getTimestamp() {
		return timestamp;
	}
	public void setTimestamp(String timestamp) {
		this.timestamp = timestamp;
	}
	public Reading getReading() {
		return reading;
	}
	public void setReading(Reading reading) {
		this.reading = reading;
	}
	public Vehicle getVehicle() {
		return vehicle;
	}
	public void setVehicle(Vehicle vehicle) {
		this.vehicle = vehicle;
	}
	
	
	

}
