package com.microservices.swiggy.restrauntservice.service;


import java.util.Iterator;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.microservices.swiggy.restrauntservice.dto.DeliveryOrderLineItemsDto;
import com.microservices.swiggy.restrauntservice.dto.DeliveryOrderRequest;
import com.microservices.swiggy.restrauntservice.dto.FilterDto;
import com.microservices.swiggy.restrauntservice.dto.MenuItemDto;
import com.microservices.swiggy.restrauntservice.dto.RestrauntInfoDto;
import com.microservices.swiggy.restrauntservice.dto.RestrauntResponse;
import com.microservices.swiggy.restrauntservice.model.MenuItem;
import com.microservices.swiggy.restrauntservice.model.RestrauntInfo;
import com.microservices.swiggy.restrauntservice.repository.MenuItemRepository;
import com.microservices.swiggy.restrauntservice.repository.RestrauntInfoRepository;
import com.microservices.swiggy.restrauntservice.repository.RestrauntSpecification;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@RequiredArgsConstructor
@Transactional
@Slf4j
public class RestrauntService {
	
	RestrauntInfoRepository repository;
	MenuItemRepository menuRepository;
	ModelMapper modelMapper;
	
	@Autowired
	public RestrauntService(RestrauntInfoRepository repository, ModelMapper modelMapper, MenuItemRepository menuRepository){
	      this.repository = repository;
	      this.modelMapper = modelMapper;
	      this.menuRepository = menuRepository;
	    }
	
	public Boolean fetchOrderStatus(DeliveryOrderRequest OrderDto) {
    	List<RestrauntResponse> responses = OrderDto.getOrderLineItemsDtoList().stream().map(menuItem -> checkAvailablity(menuItem)).toList();
    	System.out.println(responses);
    	return responses.stream().allMatch(response -> response.getIsAvailable());
    }
	
	public List<RestrauntInfoDto> getRestrauntByVariousFilters(List<FilterDto> filterDTOList)
	  {
		List<RestrauntInfo> restrauntInfoList = repository.findAll(RestrauntSpecification.columnEqual(filterDTOList));
		return restrauntInfoList.stream().map(restrauntInfo -> mapToRestrauntDto(restrauntInfo)).toList();
	  }

    public RestrauntInfoDto fetchRestrauntInfo(String restrauntName) {
    	RestrauntInfo restrauntInfo = repository.findByrestrauntName(restrauntName);
    	return mapToRestrauntDto(restrauntInfo);
    }
    
    private RestrauntInfoDto mapToRestrauntDto(RestrauntInfo restrauntInfo) {
    	RestrauntInfoDto restrauntInfoDto = new RestrauntInfoDto();
    	restrauntInfoDto.setRestrauntName(restrauntInfo.getRestrauntName());
    	restrauntInfoDto.setOperatingHours(restrauntInfo.getOperatingHours());
    	restrauntInfoDto.setCity(restrauntInfo.getCity());
    	restrauntInfoDto.setRating(restrauntInfo.getRating());
    	restrauntInfoDto.setCuisineType(restrauntInfo.getCuisineType());
    	restrauntInfoDto.setMenuItems(restrauntInfo.getMenuItems().stream().map(menuItem -> mapToMenuItemDto(menuItem)).toList());
        return restrauntInfoDto;
    }
    
    private MenuItemDto mapToMenuItemDto(MenuItem menuItem) {
    	return modelMapper.map(menuItem,MenuItemDto.class);
    }
    
    private RestrauntResponse checkAvailablity(DeliveryOrderLineItemsDto menuItem2) {
    	MenuItem menuItem = menuRepository.findByitemName(menuItem2.getItemCode());
    	if(menuItem == null) {
    		return new RestrauntResponse(menuItem2.getItemCode(),false);
    	}
    	return new RestrauntResponse(menuItem2.getItemCode(),menuItem.getIsCurrentlyAvailable());

    }
}
