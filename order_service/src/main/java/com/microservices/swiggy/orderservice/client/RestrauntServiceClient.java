package com.microservices.swiggy.orderservice.client;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import com.microservices.swiggy.orderservice.dto.DeliveryOrderRequest;

@Component
public class RestrauntServiceClient {
	
    @Autowired
    private RestTemplate template;

    public Boolean fetchOrderStatus(DeliveryOrderRequest orderDto) {
    	 String apiUrl = "http://RESTRAUNT-SERVICE/api/restraunt/order/status";
         return template.postForObject(apiUrl, orderDto, Boolean.class);
    }
}