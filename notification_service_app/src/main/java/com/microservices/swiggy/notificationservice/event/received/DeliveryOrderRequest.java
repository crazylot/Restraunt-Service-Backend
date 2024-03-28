package com.microservices.swiggy.notificationservice.event.received;

import java.util.List;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


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

