package com.ff.myShopper.dto;

import java.util.List;

import com.ff.myShopper.entity.Review;

import lombok.Data;

@Data
public class ProductWithReview {

	private String productName;
	private List<Review> reviews;
}
