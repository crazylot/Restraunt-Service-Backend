package com.microservices.swiggy.orderservice.model;

import java.time.LocalDate;
import java.time.LocalTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "t_dining_orders")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class DiningOrder {

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long bookingId;
	
	private String restrauntName;
	
	private String userName;
	
	@Column(name = "local_date", columnDefinition = "DATE")
	private LocalDate localDate;
	
	@Column(name = "local_time", columnDefinition = "TIME")
	private LocalTime localTime;

	@Column(name = "people_count")
	private Integer noOfPeople;
	
	private String orderStatus;
}

