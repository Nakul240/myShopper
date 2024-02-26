package com.ff.myShopper.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import com.ff.myShopper.dao.ProductDao;
import com.ff.myShopper.dao.ShopperDAO;
import com.ff.myShopper.dto.ResponseStructure;
import com.ff.myShopper.entity.Product;
import com.ff.myShopper.entity.Shopper;
import com.ff.myShopper.exceptions.IdNotFoundException;

@Service
public class ProductService {

	@Autowired
	private ProductDao dao;

	@Autowired
	private ShopperDAO shopperDAO;

	public ResponseEntity<ResponseStructure<Product>> saveProduct(Integer id, @RequestBody Product product) {

		Optional<Shopper> option = shopperDAO.findById(id);
		if (option.isPresent()) {
			if (option.get().getShopper_role().equals("merchant")) {
				
				product.setMerchant(option.get());
				Product saveProduct = dao.save(product);
				
				ResponseStructure<Product> structure = new ResponseStructure<Product>();
				structure.setStatusCode(HttpStatus.CREATED.value());
				structure.setMessage("Success");
				structure.setData(saveProduct);

				return new ResponseEntity<ResponseStructure<Product>>(structure, HttpStatus.CREATED);
			}
			else {
				new IdNotFoundException("Role Not Matching");
			}
			new IdNotFoundException();
		}
		
		return null;

	}
}
