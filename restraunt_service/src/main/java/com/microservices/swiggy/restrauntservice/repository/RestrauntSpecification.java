package com.microservices.swiggy.restrauntservice.repository;


import java.util.ArrayList;
import java.util.List;

import org.springframework.data.jpa.domain.Specification;

import com.microservices.swiggy.restrauntservice.dto.FilterDto;
import com.microservices.swiggy.restrauntservice.model.RestrauntInfo;

import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;

public class RestrauntSpecification

{
  public static Specification<RestrauntInfo> columnEqual(List<FilterDto> filterDTOList)
  {
    return new Specification<RestrauntInfo>() 
    {
      private static final long serialVersionUID = 1L;

      @Override
      public Predicate toPredicate(Root<RestrauntInfo> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder)
      {
        List<Predicate> predicates = new ArrayList<>();
        filterDTOList.forEach(filter -> {
        	Predicate predicate = criteriaBuilder.equal(root.get(filter.getColumnName()),filter.getColumnValue());
        	predicates.add(predicate);
        });

        return criteriaBuilder.and(predicates.toArray(new Predicate[predicates.size()]));
      }
    };
  }

  }