package com.microservices.swiggy.restrauntservice.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class MenuItemDto {
	private String itemName;
	private Double price;
	private String description;
	private Boolean isCurrentlyAvailable;
}
