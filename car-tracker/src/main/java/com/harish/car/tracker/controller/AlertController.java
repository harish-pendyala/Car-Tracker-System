package com.harish.car.tracker.controller;

import java.util.List;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.harish.car.tracker.constants.URI;
import com.harish.car.tracker.entity.Alert;
import com.harish.car.tracker.service.AlertsService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@RestController
@RequestMapping(value=URI.ALERTS)
@Api(tags="Alerts")
public class AlertController {
	
	private AlertsService service;
	
	public AlertController(AlertsService service){
		this.service = service;
		
	}
	
	@RequestMapping(method=RequestMethod.GET)
	@ApiOperation(value="Find all Alerts", notes= "Returns a list of Alerts in the app")
	@ApiResponses(value={
			@ApiResponse(code=200,message="Success"),
			@ApiResponse(code=500,message="Internal Error")
	})
	public List<Alert> findAll(){
		return service.findAll();
	}
	@RequestMapping(method=RequestMethod.GET, value=URI.ID)
	@ApiOperation(value="Find Alerts by Vin", notes= "Returns the list of Alerts existing for given vin if it exists in the app")
	@ApiResponses(value={
			@ApiResponse(code=200,message="Success"),
			@ApiResponse(code=404,message="Not Found"),
			@ApiResponse(code=500,message="Internal Error")
	})
	public List<Alert> findAlerts(@PathVariable("vin") String vin){
		return service.findAlerts(vin);
	}
	




}
