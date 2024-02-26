package com.ff.myShopper.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.ff.myShopper.dao.ProductDao;
import com.ff.myShopper.dao.ReviewDAO;
import com.ff.myShopper.dao.ShopperDAO;
import com.ff.myShopper.dto.ResponseStructure;
import com.ff.myShopper.entity.Product;
import com.ff.myShopper.entity.Review;
import com.ff.myShopper.entity.Shopper;
import com.ff.myShopper.exceptions.IdNotFoundException;

@Service
public class ReviewService {

	@Autowired
	ProductDao productDao;

	@Autowired
	ShopperDAO shopperDAO;

	@Autowired
	ReviewDAO reviewDAO;

	public ResponseEntity<ResponseStructure<Review>> addReview(Integer customerId, Integer productId, Review review) {
		Optional<Product> optProd = productDao.findById(productId);
		Optional<Shopper> optShopper = shopperDAO.findById(customerId);

		if (optProd.isPresent() && optShopper.isPresent()) {
			Product product = optProd.get();
			Shopper shopper = optShopper.get();
			if (shopper.getShopper_role().equals("customer")) {
				review.setProduct(product);
				Review saveReview = reviewDAO.save(review);

				ResponseStructure<Review> structure = new ResponseStructure<Review>();
				structure.setStatusCode(HttpStatus.CREATED.value());
				structure.setMessage("Success");
				structure.setData(saveReview);

				return new ResponseEntity<ResponseStructure<Review>>(structure, HttpStatus.CREATED);
			}
			throw new IdNotFoundException("Not a customer");
		}
		throw new IdNotFoundException();
	}

}
