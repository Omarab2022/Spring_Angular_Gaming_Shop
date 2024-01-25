package com.ecommOmar.ecomm.Services.Customer.Cart.WishList;

import com.ecommOmar.ecomm.dto.WishListDto;

import java.util.List;

public interface wishListService {

	WishListDto addProductToWishlist(WishListDto wishlistDto);

	List<WishListDto> getWishlistByUserId(Long userId) ;
}
