package com.harish.car.tracker.controller;

import java.util.List;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.harish.car.tracker.constants.URI;
import com.harish.car.tracker.entity.Reading;
import com.harish.car.tracker.entity.Vehicle;
import com.harish.car.tracker.service.ReadingsService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@RestController
@RequestMapping(value=URI.READINGS)
@Api(tags="Readings")
public class ReadingsController {
	private ReadingsService service;
	
	public ReadingsController(ReadingsService service){
		this.service = service;
		
	}
	
	@RequestMapping(method=RequestMethod.GET)
	@ApiOperation(value="Find all Readings", notes= "Returns a list of Readings in the app")
	@ApiResponses(value={
			@ApiResponse(code=200,message="Success"),
			@ApiResponse(code=500,message="Internal Error")
	})
	public List<Reading> findAll(){
		return service.findAll();
	}
	@RequestMapping(method=RequestMethod.GET, value=URI.ID)
	@ApiOperation(value="Find Readings by Vin", notes= "Returns a list of Readings for given vin if it exists in the app")
	@ApiResponses(value={
			@ApiResponse(code=200,message="Success"),
			@ApiResponse(code=404,message="Not Found"),
			@ApiResponse(code=500,message="Internal Error")
	})
	public List<Reading> findReadings(@PathVariable("vin") String vin){
		return service.findReadings(vin);
	}
	
	@CrossOrigin(origins = "http://mocker.egen.io")
	@RequestMapping(method=RequestMethod.POST)
	@ApiOperation(value="Insert  Readngs", notes= "Inserts Readings in the app with unique vin")
	@ApiResponses(value={
			@ApiResponse(code=200,message="Success"),
			@ApiResponse(code=404,message="Not Found"),
			@ApiResponse(code=500,message="Internal Error")
	})
	public Reading addReading(@RequestBody Reading reading){
        return service.addReading(reading);
	}


}
