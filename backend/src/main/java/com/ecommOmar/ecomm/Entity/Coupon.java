package com.ecommOmar.ecomm.Entity;


import jakarta.persistence.*;
import lombok.Data;

import java.util.Date;

@Entity
@Data
@Table(name="coupons")
public class Coupon {


	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id ;

	private String name ;

	private String code ;


	private Long discount ;

	private Date expirationDate ;

}
