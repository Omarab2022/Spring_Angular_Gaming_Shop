package com.ecommOmar.ecomm.dto;


import lombok.Data;

@Data
public class FAQdto {


	private Long id ;

	private String question ;

	private String answer ;

	private Long productID;
}
