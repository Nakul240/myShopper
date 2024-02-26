package com.ff.myShopper.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.ff.myShopper.dao.ProductDao;
import com.ff.myShopper.dao.ShopperDAO;
import com.ff.myShopper.dto.ProductWithReview;
import com.ff.myShopper.dto.ResponseStructure;
import com.ff.myShopper.entity.Product;
import com.ff.myShopper.entity.Shopper;
import com.ff.myShopper.entity.UserOrder;
import com.ff.myShopper.exceptions.IdNotFoundException;

@Service
public class ShopperService {

	@Autowired
	ShopperDAO dao;

	@Autowired
	ProductDao productDao;

	public ResponseEntity<ResponseStructure<Shopper>> saveShopper(Shopper shopper) {
		Shopper savedShopper = dao.save(shopper);

		ResponseStructure<Shopper> structure = new ResponseStructure<Shopper>();
		structure.setStatusCode(HttpStatus.CREATED.value());
		structure.setMessage("Success");
		structure.setData(savedShopper);

		return new ResponseEntity<ResponseStructure<Shopper>>(structure, HttpStatus.CREATED);
	}

	public ResponseEntity<ProductWithReview> findReviewOfMyProducts(Integer merchantId, Integer productId) {
		Optional<Shopper> optMerchant = dao.findById(merchantId);
		if(optMerchant.isPresent()) {
			Optional<Product> optProd = productDao.findById(productId);
			if(optProd.isPresent()) {
				Product product = optProd.get();
				
				ProductWithReview reviews = new ProductWithReview();
				reviews.setProductName(product.getProduct_name());
				reviews.setReviews(product.getReviews());
				
				ResponseStructure<ProductWithReview> structure = new ResponseStructure<ProductWithReview>();
				structure.setStatusCode(HttpStatus.OK.value());
				structure.setMessage("OK");
				structure.setData(reviews);
				
				return new ResponseEntity<ProductWithReview>(reviews, HttpStatus.OK);
				
			}
			else 
				throw new IdNotFoundException("No Product found");
		}
		else
			throw new IdNotFoundException("No Merchant found");
	}

	public ResponseEntity<ResponseStructure<List<UserOrder>>> findOrderByCustomerId(Integer customerId) {

		Optional<Shopper> optional = dao.findById(customerId);
		if(optional.isPresent()) {
			if(optional.get().getShopper_role().equals("customer")) {
				ResponseStructure<List<UserOrder>> structure = new ResponseStructure<List<UserOrder>>();
				structure.setStatusCode(HttpStatus.OK.value());
				structure.setMessage("Ok");
				structure.setData(optional.get().getOrders());
				
				return new ResponseEntity<ResponseStructure<List<UserOrder>>>(structure, HttpStatus.OK);
			}
			else
				throw new IdNotFoundException("Not a Customer");
		}
		else
			throw new IdNotFoundException();
		
	}

	public ResponseEntity<ResponseStructure<Shopper>> loginShopper(String email, String password) {
		Shopper verifedShopper = dao.findByEmailAndPassword(email,password);
		if(verifedShopper!=null) {
			ResponseStructure<Shopper> structure = new ResponseStructure<Shopper>();
			structure.setStatusCode(HttpStatus.OK.value());
			structure.setMessage("Ok");
			structure.setData(verifedShopper);
			
			return new ResponseEntity<ResponseStructure<Shopper>>(structure, HttpStatus.OK);
		}
		else {
			throw new IdNotFoundException();
		}
	}
}
