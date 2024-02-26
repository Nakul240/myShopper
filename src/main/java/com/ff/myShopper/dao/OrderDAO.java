package com.ff.myShopper.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ff.myShopper.entity.UserOrder;

public interface OrderDAO extends JpaRepository<UserOrder, Integer>{

}
