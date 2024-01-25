package com.ecommOmar.ecomm.Controller.customer;


import com.ecommOmar.ecomm.Repository.WishListRepository;
import com.ecommOmar.ecomm.Services.Customer.Cart.WishList.wishListServiceImpl;
import com.ecommOmar.ecomm.dto.WishListDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.ecommOmar.ecomm.Services.Customer.Cart.WishList.wishListService;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/customer")
public class wishlistController {

	private final wishListService wishListService;


	@PostMapping("/wishlist")
	public ResponseEntity<?> addProductToWishlist(@RequestBody WishListDto wishlistDto) {
		WishListDto postedWishlistDto = wishListService.addProductToWishlist(wishlistDto);
		if (postedWishlistDto == null) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Something went wrong");
		}
		return ResponseEntity.status(HttpStatus.CREATED).body(postedWishlistDto);
	}


	@GetMapping("/wishlist/{userId}")
	public ResponseEntity<List<WishListDto>> getWishlistByUserId(@PathVariable Long userId) {
		return ResponseEntity.ok(wishListService.getWishlistByUserId(userId));
	}

}
