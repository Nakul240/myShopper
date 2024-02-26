package com.ff.myShopper.dto;

import java.util.List;

import com.ff.myShopper.entity.UserOrder;

import lombok.Data;

@Data
public class OrderRequest {

	private UserOrder order;

	private List<Integer> productIds;
}
