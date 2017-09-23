package com.harish.car.tracker.controller;

import java.util.List;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.harish.car.tracker.constants.URI;
import com.harish.car.tracker.entity.Vehicle;
import com.harish.car.tracker.service.VehicleService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@RestController
@RequestMapping(value=URI.VEHICLES)
@Api(tags="Vehicles")
public class VehicleController {
	
	private VehicleService service;
	
	public VehicleController(VehicleService service){
		this.service = service;
		
	}
	
	@RequestMapping(method=RequestMethod.GET)
	@ApiOperation(value="Find all Vehicles", notes= "Returns a list of Vehicles in the app")
	@ApiResponses(value={
			@ApiResponse(code=200,message="Success"),
			@ApiResponse(code=500,message="Internal Error")
	})
	public List<Vehicle> findAll(){
		return service.findAll();
	}
	@RequestMapping(method=RequestMethod.GET, value=URI.ID)
	@ApiOperation(value="Find Vehicle by Vin", notes= "Returns a Vehicle if it exists in the app")
	@ApiResponses(value={
			@ApiResponse(code=200,message="Success"),
			@ApiResponse(code=404,message="Not Found"),
			@ApiResponse(code=500,message="Internal Error")
	})
	public Vehicle findOne(@PathVariable("vin") String vin){
		return service.findOne(vin);
	}
	
	@CrossOrigin(origins = "http://mocker.egen.io")
	@RequestMapping(method=RequestMethod.PUT)
	@ApiOperation(value="Insert  Vehicles", notes= "Inserts Vehicles in the app with unique vin")
	@ApiResponses(value={
			@ApiResponse(code=200,message="Success"),
			@ApiResponse(code=404,message="Not Found"),
			@ApiResponse(code=500,message="Internal Error")
	})
	public void loadVehicles(@RequestBody Vehicle[] vehicles){
		for(Vehicle v : vehicles) {
            System.out.println(v.toString());
        }
        service.loadVehicles(vehicles);
	}



}
