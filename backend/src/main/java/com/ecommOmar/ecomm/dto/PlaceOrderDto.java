package com.ecommOmar.ecomm.dto;


import lombok.Data;

@Data
public class PlaceOrderDto {

	private Long userId;

	private String address ;

	private String orderDescription ;
}
