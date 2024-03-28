package com.microservices.swiggy.orderservice.service;

import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.microservices.swiggy.orderservice.client.NotificationServiceClient;
import com.microservices.swiggy.orderservice.client.PaymentServiceClient;
import com.microservices.swiggy.orderservice.client.RestrauntServiceClient;
import com.microservices.swiggy.orderservice.dto.DeliveryOrderLineItemsDto;
import com.microservices.swiggy.orderservice.dto.DeliveryOrderRequest;
import com.microservices.swiggy.orderservice.dto.DiningOrderRequest;
import com.microservices.swiggy.orderservice.dto.InvoiceReceiptDto;
import com.microservices.swiggy.orderservice.model.DeliveryOrder;
import com.microservices.swiggy.orderservice.model.DeliveryOrderLineItems;
import com.microservices.swiggy.orderservice.model.DiningOrder;
import com.microservices.swiggy.orderservice.repository.DeliveryOrderRepository;
import com.microservices.swiggy.orderservice.repository.DiningOrderRepository;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@RequiredArgsConstructor
@Transactional
@Slf4j
public class OrderService {

    private final DeliveryOrderRepository deliveryOrderRepository;
    private final DiningOrderRepository diningOrderRepository;
    private final ModelMapper modelMapper;
    private final RestrauntServiceClient restrauntServiceClient;
    private final PaymentServiceClient paymentServiceClient;
    private final NotificationServiceClient notificationServiceClient;

    public String placeDiningOrder(DiningOrderRequest diningOrderRequestDto) {
        DiningOrder diningOrder = new DiningOrder();
        diningOrder.setRestrauntName(diningOrderRequestDto.getRestrauntName());
        diningOrder.setLocalDate(diningOrderRequestDto.getLocalDate());
        diningOrder.setLocalTime(diningOrderRequestDto.getLocalTime());
        diningOrder.setNoOfPeople(diningOrderRequestDto.getNoOfPeople());
        try {
        	diningOrderRepository.save(diningOrder);
		} catch (Exception e) {
			throw new IllegalArgumentException("An Error occurred while persisting the item in database");
		}
        return "Dining Order Placed";
    }
    
    
    public InvoiceReceiptDto placeDeliveryOrder(DeliveryOrderRequest deliveryOrderRequestDto) {
    	Boolean validOrder = restrauntServiceClient.fetchOrderStatus(deliveryOrderRequestDto);
    	
    	DeliveryOrder deliveryOrder = new DeliveryOrder();
    	deliveryOrder.setUserName(deliveryOrderRequestDto.getUserName());
    	deliveryOrder.setRestrauntName(deliveryOrderRequestDto.getRestrauntName());
    	deliveryOrder.setModeOfPayment(deliveryOrderRequestDto.getModeOfPayment());
    	deliveryOrder.setTotalAmount(deliveryOrderRequestDto.getTotalAmount());
    	
    	List<DeliveryOrderLineItems> deliveryOrderLineItems = deliveryOrderRequestDto.getOrderLineItemsDtoList().stream().map(orderItem -> mapToDeliveryOrderLineItemsEntity(orderItem, deliveryOrder)).toList();
    	deliveryOrder.setOrderLineItemsList(deliveryOrderLineItems);
    	
    	if(Boolean.TRUE.equals(validOrder)) {
    		InvoiceReceiptDto invoice = paymentServiceClient.fetchPaymnentStatusAndGenerateReceipt(deliveryOrderRequestDto);
    		if(Boolean.TRUE.equals(invoice.getPaymentStatus())) {
    			notificationServiceClient.ConsumerNotification("Confirmed");
    			notificationServiceClient.RestaurantNotification(deliveryOrderRequestDto);
    			deliveryOrder.setOrderStatus("Confirmed");
                try {
                	deliveryOrderRepository.save(deliveryOrder);
        		} catch (Exception e) {
        			throw new IllegalArgumentException("An Error occurred while persisting the item in database");
        		}
                return invoice;
    		}else {
    			return new InvoiceReceiptDto("", null, 0, null, null, validOrder, "Payment Cancelled, refund will start soon");
    		}
    		
    	}
    	else {
    		notificationServiceClient.ConsumerNotification("Cancelled");
    		deliveryOrder.setOrderStatus("Cancelled");
    		try {
            	deliveryOrderRepository.save(deliveryOrder);
    		} catch (Exception e) {
    			throw new IllegalArgumentException("An Error occurred while persisting the item in database");
    		}
    		return new InvoiceReceiptDto("", null, 0, null, null, validOrder, "Order Cancelled");
    	}
    }
    
    private DeliveryOrderLineItems mapToDeliveryOrderLineItemsEntity(DeliveryOrderLineItemsDto orderLineItemDto,DeliveryOrder deliveryOrder) {
    	DeliveryOrderLineItems deliveryOrderLineItem = new DeliveryOrderLineItems();
    	deliveryOrderLineItem.setItemCode(orderLineItemDto.getItemCode());
    	deliveryOrderLineItem.setPrice(orderLineItemDto.getPrice());
    	deliveryOrderLineItem.setQuantity(orderLineItemDto.getQuantity());
    	deliveryOrderLineItem.setDeliveryOrder(deliveryOrder);
    	return deliveryOrderLineItem;
    }
}
