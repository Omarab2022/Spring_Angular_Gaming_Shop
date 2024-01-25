package com.ecommOmar.ecomm.dto;


import com.ecommOmar.ecomm.Enums.UserRole;
import lombok.Data;

@Data
public class UserDto {

	private long id ;

	private String email ;

	private String name ;

	private UserRole userRole ;
}
