package com.microservices.swiggy.orderservice.dto;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class InvoiceReceiptDto {

    private String orderId;
    private String name;
    private double price;
    private String orderDate;
    private String estimateDeliveryWindow;
    private Boolean paymentStatus;
    private String msg;
}