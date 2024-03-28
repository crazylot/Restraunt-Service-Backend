package com.microservices.swiggy.orderservice.client;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import com.microservices.swiggy.orderservice.dto.DeliveryOrderRequest;
import com.microservices.swiggy.orderservice.dto.InvoiceReceiptDto;

@Component
public class PaymentServiceClient {
	
    @Autowired
    private RestTemplate template;

    public InvoiceReceiptDto fetchPaymnentStatusAndGenerateReceipt(DeliveryOrderRequest orderDto) {
    	 String apiUrl = "http://PAYMENT-SERVICE/api/payment/status";
         return template.postForObject(apiUrl, orderDto, InvoiceReceiptDto.class);
    }
}