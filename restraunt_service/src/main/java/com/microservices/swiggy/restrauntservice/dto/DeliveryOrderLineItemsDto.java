package com.microservices.swiggy.restrauntservice.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DeliveryOrderLineItemsDto {
    private Long id;
    private String itemCode;
    private BigDecimal price;
    private Integer quantity;
}
