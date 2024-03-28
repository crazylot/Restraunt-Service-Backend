package com.microservices.swiggy.paymentservice.service;


import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;

import org.springframework.stereotype.Service;

import com.microservices.swiggy.paymentservice.dto.DeliveryOrderRequest;
import com.microservices.swiggy.paymentservice.dto.InvoiceReceipt;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class PaymentService {
	
	
	public Boolean fetchPaymentStatus(DeliveryOrderRequest OrderDto) {
    	return true;
    }
	
    public InvoiceReceipt generateInvoiceReceipt(DeliveryOrderRequest OrderDto) {
    	Date currentDate = new Date();
    	SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    	String currentDateTime = dateFormat.format(currentDate);
    	
    	UUID id = UUID.randomUUID();    	
    	
    	InvoiceReceipt invoice = new InvoiceReceipt();
    	invoice.setOrderId(id.toString());
    	invoice.setName(OrderDto.getUserName());
    	invoice.setPrice(OrderDto.getTotalAmount());
    	invoice.setOrderDate(currentDateTime);
    	invoice.setEstimateDeliveryWindow("1 hr");
    	invoice.setPaymentStatus(fetchPaymentStatus(OrderDto));
    	invoice.setMsg("Order Placed");
    	return invoice;
    }
    
}
