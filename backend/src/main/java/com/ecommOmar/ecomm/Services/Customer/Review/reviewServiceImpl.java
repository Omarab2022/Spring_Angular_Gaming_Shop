package com.ecommOmar.ecomm.Services.Customer.Review;


import com.ecommOmar.ecomm.Entity.*;
import com.ecommOmar.ecomm.Repository.OrderRepository;
import com.ecommOmar.ecomm.Repository.ProductRepository;
import com.ecommOmar.ecomm.Repository.ReviewRepository;
import com.ecommOmar.ecomm.Repository.userRepository;
import com.ecommOmar.ecomm.dto.ProductDto;
import com.ecommOmar.ecomm.dto.ReviewDto;
import com.ecommOmar.ecomm.dto.orderProductResponseDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class reviewServiceImpl implements reviewService {


	private final OrderRepository orderRepository;


	private final userRepository userRepository;

	private final ReviewRepository reviewRepository;

	private final ProductRepository productRepository;

	public orderProductResponseDTO getOrderedProductsDetailsByOrderId(Long orderId) {
		Optional<Order> optionalOrder = orderRepository.findById(orderId);
		orderProductResponseDTO orderedProductsResponseDto = new orderProductResponseDTO();

		if (optionalOrder.isPresent()) {
			orderedProductsResponseDto.setOrderAmount(optionalOrder.get().getAmount());
			List<ProductDto> productOtoList = new ArrayList<>();

			for (CartItemes cartItems : optionalOrder.get().getCartItems()) {
				ProductDto productOto = new ProductDto();
				productOto.setId(cartItems.getProduct().getId());
				productOto.setName(cartItems.getProduct().getName());
				productOto.setPrice(cartItems.getPrice());
				productOto.setQuantity(cartItems.getQuantity());
				productOto.setByteImg(cartItems.getProduct().getImg());

				productOtoList.add(productOto);
			}

			orderedProductsResponseDto.setProductDtoList(productOtoList);
		}

		return orderedProductsResponseDto;
	}

	public ReviewDto giveReview(ReviewDto reviewDto) throws IOException {
		Optional<Product> optionalProduct = productRepository.findById(reviewDto.getProductId());
		Optional<User> optionalUser = userRepository.findById(reviewDto.getUserId());

		if (optionalProduct.isPresent() && optionalUser.isPresent()) {
			Review review = new Review();
			review.setRating(reviewDto.getRating());
			review.setDescription(reviewDto.getDescription());
			review.setUser(optionalUser.get());
			review.setProduct(optionalProduct.get());
			review.setImg(reviewDto.getImg().getBytes());
			return reviewRepository.save(review).getDto();
		}

		return null;
	}

}
