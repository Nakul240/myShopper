package com.ff.myShopper.entity;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import lombok.Data;

@Data 
@Entity
public class Product {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer product_id;
	private String product_name;
	private Double product_price;
	
	@JsonIgnore
	@ManyToMany(mappedBy = "products")
	private List<UserOrder> orders;

	@JsonIgnore
	@ManyToOne
	@JoinColumn(name="merchant_id")
	private Shopper merchant;
	
	@OneToMany(mappedBy = "product",cascade = CascadeType.ALL)
	private List<Review> reviews;
}
