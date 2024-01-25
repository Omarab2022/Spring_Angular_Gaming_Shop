package com.ecommOmar.ecomm.Services.Customer.Review;

import com.ecommOmar.ecomm.dto.ReviewDto;
import com.ecommOmar.ecomm.dto.orderProductResponseDTO;

import java.io.IOException;

public interface reviewService {


	orderProductResponseDTO getOrderedProductsDetailsByOrderId(Long orderId);

	ReviewDto giveReview(ReviewDto reviewDto) throws IOException;
}
