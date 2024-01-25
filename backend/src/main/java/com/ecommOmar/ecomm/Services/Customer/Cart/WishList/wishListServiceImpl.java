package com.ecommOmar.ecomm.Services.Customer.Cart.WishList;


import com.ecommOmar.ecomm.Entity.Product;
import com.ecommOmar.ecomm.Entity.User;
import com.ecommOmar.ecomm.Entity.WishList;
import com.ecommOmar.ecomm.Repository.ProductRepository;
import com.ecommOmar.ecomm.Repository.WishListRepository;
import com.ecommOmar.ecomm.dto.WishListDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import com.ecommOmar.ecomm.Repository.userRepository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class wishListServiceImpl implements wishListService{

	private final userRepository userRepository;

	private final ProductRepository productRepository;

	private final WishListRepository wishlistRepository;

	public WishListDto addProductToWishlist(WishListDto wishlistDto) {
		Optional<Product> optionalProduct = productRepository.findById(wishlistDto.getProductId());
		Optional<User> optionalUser = userRepository.findById(wishlistDto.getUserId());

		if (optionalProduct.isPresent() && optionalUser.isPresent()) {
			WishList wishlist = new WishList();
			wishlist.setProduct(optionalProduct.get());
			wishlist.setUser(optionalUser.get());
			return wishlistRepository.save(wishlist).getWishlistDto();
		}

		return null;
	}


	public List<WishListDto> getWishlistByUserId(Long userId) {
		return wishlistRepository.findAllByUserId(userId)
				.stream()
				.map(WishList::getWishlistDto)
				.collect(Collectors.toList());
	}

}
