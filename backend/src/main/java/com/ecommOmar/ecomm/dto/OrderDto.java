package com.ecommOmar.ecomm.dto;


import com.ecommOmar.ecomm.Entity.CartItemes;
import com.ecommOmar.ecomm.Entity.User;
import com.ecommOmar.ecomm.Enums.OrderStatus;
import jakarta.persistence.*;
import lombok.Data;

import java.util.Date;
import java.util.List;
import java.util.UUID;

@Data
public class OrderDto {


	private Long id ;

	private String orderDescription ;

	private Date date ;

	private Long amount ;

	private String address ;


	private String payment ;

	private OrderStatus orderStatus ;

	private Long totalAmount ;

	private Long discount ;

	private UUID trackingId ;

	private String userName ;


	private List<cartDto> cartItems ;


	private String couponName;
}
