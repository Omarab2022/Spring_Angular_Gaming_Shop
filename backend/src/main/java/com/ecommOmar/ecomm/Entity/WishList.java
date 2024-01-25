package com.ecommOmar.ecomm.Entity;


import com.ecommOmar.ecomm.dto.WishListDto;
import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

@Data
@Entity
public class WishList {


	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id ;

	@ManyToOne(fetch = FetchType.LAZY , optional = false)
	@JoinColumn(name = "user_id" , nullable = false)
	@OnDelete(action = OnDeleteAction.CASCADE)
	private User user ;


	@ManyToOne(fetch = FetchType.LAZY , optional = false)
	@JoinColumn(name = "product_id" , nullable = false)
	@OnDelete(action = OnDeleteAction.CASCADE)
	private Product product ;

	public WishListDto getWishlistDto() {
		WishListDto wishlistDto = new WishListDto();

		wishlistDto.setId(id);
		wishlistDto.setProductId(product.getId());
		wishlistDto.setReturnedImg(product.getImg());
		wishlistDto.setProductName(product.getName());
		wishlistDto.setProductDescription(product.getDescription());
		wishlistDto.setPrice(product.getPrice());
		wishlistDto.setUserId(user.getId());

		return wishlistDto;
	}

}
