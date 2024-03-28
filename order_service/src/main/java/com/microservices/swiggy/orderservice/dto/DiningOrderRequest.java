package com.microservices.swiggy.orderservice.dto;


import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class DiningOrderRequest {
	private String restrauntName;
	
	private java.time.LocalDate localDate;
	
	private java.time.LocalTime localTime;
	
	private int noOfPeople;
	
}
