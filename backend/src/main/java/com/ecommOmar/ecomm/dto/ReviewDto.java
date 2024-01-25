package com.ecommOmar.ecomm.dto;


import com.ecommOmar.ecomm.Entity.Product;
import com.ecommOmar.ecomm.Entity.User;
import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
import org.springframework.web.multipart.MultipartFile;

@Data
public class ReviewDto {



	private long id ;


	private Long rating ;


	private String description;


	private MultipartFile img ;


	private byte[]  returnedImg ;


	private Long userId ;


	private String username ;

	private Long productId ;


}
