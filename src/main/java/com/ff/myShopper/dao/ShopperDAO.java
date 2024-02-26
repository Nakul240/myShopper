package com.ff.myShopper.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.ff.myShopper.entity.Shopper;

@Repository
public interface ShopperDAO extends JpaRepository<Shopper, Integer>{

	@Query("Select s from Shopper s where s.shopper_email=?1 AND s.shopper_password=?2")
	Shopper findByEmailAndPassword(String email, String password);

	
}
