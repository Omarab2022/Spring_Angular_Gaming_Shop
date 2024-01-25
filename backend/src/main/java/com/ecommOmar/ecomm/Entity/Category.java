package com.ecommOmar.ecomm.Entity;


import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name="category")
@Data
public class Category {


	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id ;


	private String name;

	@Lob //large data
	private String description;
}
