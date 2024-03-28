package com.microservices.swiggy.restrauntservice.dto;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RestrauntInfoDto {
	private String restrauntName;
	private String operatingHours;
	private String city;
	private Double rating;
	private String cuisineType;
	private List<MenuItemDto> menuItems;
}
