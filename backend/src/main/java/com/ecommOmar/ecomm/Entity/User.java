package com.ecommOmar.ecomm.Entity;


import com.ecommOmar.ecomm.Enums.UserRole;
import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
@Table(name="users")
public class User {


	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id ;

	private String name ;

	private String password ;

	private String email ;

	private UserRole role ;



	@Lob //large data
	@Column(columnDefinition = "longblob")
	private byte[] img ;


}
