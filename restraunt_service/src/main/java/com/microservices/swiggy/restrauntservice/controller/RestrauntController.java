package com.microservices.swiggy.restrauntservice.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.microservices.swiggy.restrauntservice.dto.DeliveryOrderRequest;
import com.microservices.swiggy.restrauntservice.dto.FilterDto;
import com.microservices.swiggy.restrauntservice.dto.RestrauntInfoDto;
import com.microservices.swiggy.restrauntservice.service.RestrauntService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/api/restraunt")
@RequiredArgsConstructor
@Slf4j
public class RestrauntController {
	
	RestrauntService service;
	
	@Autowired
	public RestrauntController(RestrauntService service){
	      this.service = service;

	    }
	
	@PostMapping(value = "/order/status", consumes = "application/json")
	public Boolean returnOrderStatus(@RequestBody DeliveryOrderRequest orderDto) {
		log.info("fetching order status..");
		return service.fetchOrderStatus(orderDto);
		
	}
	
	@GetMapping("/details/{restrauntName}")
	public ResponseEntity<RestrauntInfoDto> getRestrauntDetails(@PathVariable String restrauntName) {
		log.info("fetching restraunt info..");
		return ResponseEntity.ok().body(service.fetchRestrauntInfo(restrauntName));
		
	}
	
	/**
	 * This is to fetch restraunt related details and food items offered by it
	 * @param filterDTOList    list of FilterDto
	 * @return list of Restraunts on the basis of specfied filters. 
	*/
    @PostMapping("/filters")
	public ResponseEntity<List<RestrauntInfoDto>> getRestrauntByVariousFilters(@RequestBody List<FilterDto> filterDTOList)
    {
    	return ResponseEntity.accepted().body(service.getRestrauntByVariousFilters(filterDTOList));
		
	}
	
}
