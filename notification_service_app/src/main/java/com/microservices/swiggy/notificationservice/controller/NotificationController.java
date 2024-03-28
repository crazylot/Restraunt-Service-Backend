package com.microservices.swiggy.notificationservice.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.microservices.swiggy.notificationservice.event.received.DeliveryOrderRequest;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RestController
@RequiredArgsConstructor
@Slf4j
public class NotificationController {
	
	@GetMapping("/api/notification/orderstatus/{status}")
	public String logOrderForUser(@PathVariable String status) {
		log.info(status);
		return "logged Order Status";
	}
	
	@PostMapping(value = "/api/notification/restaurant/confirmation", consumes = "application/json")
	public String logRestrauntDetailsForOrder(@RequestBody DeliveryOrderRequest orderDto) {
		log.info(""+orderDto);
		return "logged Order Details for Restraunt";
	}
	
}
