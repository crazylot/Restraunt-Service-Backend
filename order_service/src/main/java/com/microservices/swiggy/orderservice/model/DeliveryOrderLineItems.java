package com.microservices.swiggy.orderservice.model;

import java.math.BigDecimal;

import com.fasterxml.jackson.annotation.JsonBackReference;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name = "t_order_line_items")
@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class DeliveryOrderLineItems {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String itemCode;
    private BigDecimal price;
    private Integer quantity;
    
    @ManyToOne
    @JsonBackReference
    @JoinColumn(name = "order_id")
    @ToString.Exclude
    private DeliveryOrder deliveryOrder;
}
