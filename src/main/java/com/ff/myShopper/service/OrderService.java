package com.ff.myShopper.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.ff.myShopper.dao.OrderDAO;
import com.ff.myShopper.dao.ProductDao;
import com.ff.myShopper.dao.ShopperDAO;
import com.ff.myShopper.dto.OrderRequest;
import com.ff.myShopper.dto.ResponseStructure;
import com.ff.myShopper.entity.Product;
import com.ff.myShopper.entity.Shopper;
import com.ff.myShopper.entity.UserOrder;
import com.ff.myShopper.exceptions.IdNotFoundException;

@Service
public class OrderService {

	@Autowired
	private OrderDAO dao;
	
	@Autowired
	private ShopperDAO shopperDAO;
	
	@Autowired
	private ProductDao productDao;
	
	public ResponseEntity<ResponseStructure<UserOrder>> createOrder(Integer id, OrderRequest request) {
		
		Optional<Shopper> optional = shopperDAO.findById(id);
		if(optional.isPresent()) {
			if(optional.get().getShopper_role().equals("customer")) {
				UserOrder order = request.getOrder();
				List<Product> products = new ArrayList<Product>();
				List<Integer> productIds = request.getProductIds();
				for (Integer productId : productIds) {
					products.add(productDao.findById(productId).get());
				}
				order.setProducts(products);
				order.setShopper(optional.get());
				dao.save(order);
				
				ResponseStructure<UserOrder> structure = new ResponseStructure<UserOrder>();
				structure.setStatusCode(HttpStatus.CREATED.value());
				structure.setMessage("Success");
				structure.setData(order);
				
				return new ResponseEntity<ResponseStructure<UserOrder>>(structure, HttpStatus.CREATED);
			}else 
			throw new IdNotFoundException("Not a Customer");
		}
		else 
			throw new IdNotFoundException();
	}

	
}
