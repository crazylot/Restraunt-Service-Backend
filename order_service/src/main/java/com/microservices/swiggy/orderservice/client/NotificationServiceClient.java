package com.microservices.swiggy.orderservice.client;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import com.microservices.swiggy.orderservice.dto.DeliveryOrderRequest;

@Component
public class NotificationServiceClient {
	
    @Autowired
    private RestTemplate template;

    public String ConsumerNotification(String status) {
    	 String apiUrl = "http://NOTIFICATIONSERVICE/api/notification/orderstatus/" +status;
    	 return template.getForObject(apiUrl, String.class);
    }
    public String RestaurantNotification(DeliveryOrderRequest orderDto) {
   	 String apiUrl = "http://NOTIFICATIONSERVICE/api/notification/restaurant/confirmation";
        return template.postForObject(apiUrl, orderDto, String.class);
   }
}