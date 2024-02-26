package com.ff.myShopper.dto;

import lombok.Data;

@Data
public class ResponseStructure<T> {

	private Integer statusCode;
	private String message;
	private T data;
}
