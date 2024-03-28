package com.microservices.swiggy.restrauntservice.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import jakarta.persistence.*;
import java.util.List;

@Entity
@Table(name = "t_restraunt_info")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class RestrauntInfo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long restrauntId;
    
    private String restrauntName;
    private String operatingHours;
	private String city;
	private Double rating;
	private String cuisineType;
     
    @OneToMany(cascade = CascadeType.ALL)
    private List<MenuItem> menuItems;
}
