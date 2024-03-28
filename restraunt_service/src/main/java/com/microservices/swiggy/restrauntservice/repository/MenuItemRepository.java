package com.microservices.swiggy.restrauntservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.microservices.swiggy.restrauntservice.model.MenuItem;

@Repository
public interface MenuItemRepository extends JpaRepository<MenuItem, Long>{

	public MenuItem findByitemName(String itemName);
}