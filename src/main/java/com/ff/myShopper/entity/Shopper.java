package com.ff.myShopper.entity;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import lombok.Data;

@Data
@Entity
public class Shopper {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int shopper_id;
	private String shopper_name;
	private String shopper_email;
	
	private String shopper_password;
	private String shopper_role;

	@JsonIgnore
	@OneToMany(mappedBy = "shopper",cascade = CascadeType.ALL)
	private List<UserOrder> orders;

	@JsonIgnore
	@OneToMany(mappedBy = "merchant",cascade = CascadeType.ALL)
	private List<Product> products;
}
