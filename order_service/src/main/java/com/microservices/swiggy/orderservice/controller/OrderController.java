package com.microservices.swiggy.orderservice.controller;


import java.util.concurrent.CompletableFuture;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.microservices.swiggy.orderservice.dto.DeliveryOrderRequest;
import com.microservices.swiggy.orderservice.dto.DiningOrderRequest;
import com.microservices.swiggy.orderservice.dto.InvoiceReceiptDto;
import com.microservices.swiggy.orderservice.service.OrderService;

import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.retry.annotation.Retry;
import io.github.resilience4j.timelimiter.annotation.TimeLimiter;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/api/order")
@RequiredArgsConstructor
@Slf4j
public class OrderController {

    private final OrderService orderService;
    
    @PostMapping("/dining")
    public ResponseEntity<String> placeDiningOrder(@RequestBody DiningOrderRequest diningRequest){
    	log.info("Placing Dining Order Request");
    	return ResponseEntity.ok(orderService.placeDiningOrder(diningRequest));
    }
    
    @PostMapping("/delivery")
    @ResponseStatus(HttpStatus.CREATED)
    @CircuitBreaker(name = "restraunt", fallbackMethod = "fallbackMethod")
    @TimeLimiter(name = "restraunt")
    @Retry(name = "restraunt")
    public CompletableFuture<InvoiceReceiptDto> placeDeliveryOrder(@RequestBody DeliveryOrderRequest deliveryRequest){
    	log.info("Placing Delivery Order Request");
    	return CompletableFuture.supplyAsync(() -> orderService.placeDeliveryOrder(deliveryRequest));
    }
    public CompletableFuture<InvoiceReceiptDto> fallbackMethod(DeliveryOrderRequest deliveryRequest, RuntimeException runtimeException) {
    	log.info("Cannot Place Order Executing Fallback logic");
    	log.info("Returning Default Response --> Empty Payment Receipt");
    	return CompletableFuture.supplyAsync(() -> new InvoiceReceiptDto("", null, 0, null, null, null, "Restraunt or Payment or Notification Service Down"));
    }
    
}
