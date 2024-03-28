package com.microservices.swiggy.paymentservice.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.microservices.swiggy.paymentservice.dto.DeliveryOrderRequest;
import com.microservices.swiggy.paymentservice.dto.InvoiceReceipt;
import com.microservices.swiggy.paymentservice.service.PaymentService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/payment")
@RequiredArgsConstructor
public class PaymentController {
	
	@Autowired
	private PaymentService service;
	
	@PostMapping(value = "/status", consumes = "application/json")
	public InvoiceReceipt returnOrderStatus(@RequestBody DeliveryOrderRequest orderDto) {
		return service.generateInvoiceReceipt(orderDto);
		
	}
	
}
