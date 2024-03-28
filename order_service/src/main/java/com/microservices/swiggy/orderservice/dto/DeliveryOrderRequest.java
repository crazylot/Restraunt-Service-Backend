package com.microservices.swiggy.orderservice.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DeliveryOrderRequest {
	
	private String restrauntName;
	private String userName;
	private String modeOfPayment;
    private List<DeliveryOrderLineItemsDto> orderLineItemsDtoList;
    private Double totalAmount;
}
