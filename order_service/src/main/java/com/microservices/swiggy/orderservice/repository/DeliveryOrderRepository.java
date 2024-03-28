package com.microservices.swiggy.orderservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.microservices.swiggy.orderservice.model.DeliveryOrder;

public interface DeliveryOrderRepository extends JpaRepository<DeliveryOrder, Long> {
}
