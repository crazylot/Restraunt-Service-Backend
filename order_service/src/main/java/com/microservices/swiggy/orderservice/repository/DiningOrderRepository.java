package com.microservices.swiggy.orderservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.microservices.swiggy.orderservice.model.DiningOrder;

public interface DiningOrderRepository extends JpaRepository<DiningOrder, Long>{

}
