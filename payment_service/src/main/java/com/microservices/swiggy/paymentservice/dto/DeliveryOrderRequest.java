package com.microservices.swiggy.paymentservice.dto;

import java.util.List;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

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
