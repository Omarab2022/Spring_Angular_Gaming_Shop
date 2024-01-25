package com.ecommOmar.ecomm.dto;

import lombok.Data;

import java.util.Date;

@Data
public class CouponDto {

	private long id ;

	private String name ;

	private String code ;


	private Long discount ;

	private Date expirationDate ;
}
