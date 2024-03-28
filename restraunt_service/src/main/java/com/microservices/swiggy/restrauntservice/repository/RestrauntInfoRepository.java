package com.microservices.swiggy.restrauntservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import com.microservices.swiggy.restrauntservice.model.RestrauntInfo;

@Repository
public interface RestrauntInfoRepository extends JpaRepository<RestrauntInfo, Long>, JpaSpecificationExecutor<RestrauntInfo>{

	public RestrauntInfo findByrestrauntName(String restrauntName);
}
