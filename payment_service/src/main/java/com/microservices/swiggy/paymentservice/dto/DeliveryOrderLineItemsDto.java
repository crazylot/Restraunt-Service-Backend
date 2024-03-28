package com.microservices.swiggy.paymentservice.dto;

import java.math.BigDecimal;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DeliveryOrderLineItemsDto {
    private Long id;
    private String itemCode;
    private BigDecimal price;
    private Integer quantity;
}
