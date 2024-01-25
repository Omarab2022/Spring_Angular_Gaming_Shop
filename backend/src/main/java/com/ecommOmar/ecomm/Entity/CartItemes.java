package com.ecommOmar.ecomm.Entity;


import com.ecommOmar.ecomm.dto.cartDto;
import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

@Entity
@Data
public class CartItemes {


	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id ;

	private Long price ;

	private Long quantity ;

	@ManyToOne(fetch = FetchType.LAZY , optional = false)
	@JoinColumn(name = "product_id" ,nullable = false)
	@OnDelete(action = OnDeleteAction.CASCADE)
	private Product product ;



	@ManyToOne(fetch = FetchType.LAZY , optional = false)
	@JoinColumn(name = "user_id" ,nullable = false)
	@OnDelete(action = OnDeleteAction.CASCADE)
	private User user ;


	@OneToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "order_id" )
	private Order order ;

	public cartDto getCartDto(){

		cartDto cartDto = new cartDto();

		cartDto.setId(id);
		cartDto.setPrice(price);
		cartDto.setProductId(product.getId());
		cartDto.setQuantity(quantity);
		cartDto.setUserId(user.getId());
		cartDto.setProductName(product.getName());
		cartDto.setReturnedImg(product.getImg());

		return cartDto ;
	}
}

