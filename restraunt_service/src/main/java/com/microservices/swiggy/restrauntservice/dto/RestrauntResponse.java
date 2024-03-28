package com.microservices.swiggy.restrauntservice.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class RestrauntResponse {	
	
	private String itemName;
	private Boolean isAvailable;
	
	
}
