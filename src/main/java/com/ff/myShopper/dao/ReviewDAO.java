package com.ff.myShopper.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ff.myShopper.entity.Review;

public interface ReviewDAO extends JpaRepository<Review, Integer>{

}
