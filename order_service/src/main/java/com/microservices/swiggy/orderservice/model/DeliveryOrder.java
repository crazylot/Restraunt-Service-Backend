package com.microservices.swiggy.orderservice.model;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name = "t_orders")
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class DeliveryOrder {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long orderId;
    private String userName;
    private String restrauntName;
     
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "deliveryOrder", orphanRemoval = true)
    @JsonManagedReference
    @ToString.Exclude
    private List<DeliveryOrderLineItems> orderLineItemsList = new ArrayList<>();
    
    private String modeOfPayment;
    private Double totalAmount;
    private String paymentConfirmation;
    private String orderStatus;
}
