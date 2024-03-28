package com.microservices.swiggy.notificationservice.event.received;


import java.math.BigDecimal;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DeliveryOrderLineItemsDto {
    private Long id;
    private String itemCode;
    private BigDecimal price;
    private Integer quantity;
}
