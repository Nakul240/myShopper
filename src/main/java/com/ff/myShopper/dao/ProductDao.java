package com.ff.myShopper.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ff.myShopper.entity.Product;

@Repository
public interface ProductDao extends JpaRepository<Product, Integer>{
	
}
