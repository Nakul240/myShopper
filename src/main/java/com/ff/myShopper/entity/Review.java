package com.ff.myShopper.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Data;

@Data
@Entity
public class Review {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer review_id;
	private Integer review_rating;
	private String review_description;

	@JsonIgnore
	@ManyToOne
	@JoinColumn(name = "product_id")
	private Product product;
}
