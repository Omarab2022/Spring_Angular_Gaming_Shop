package com.ecommOmar.ecomm.Controller.customer;

import com.ecommOmar.ecomm.Services.Customer.Review.reviewService;
import com.ecommOmar.ecomm.dto.ReviewDto;
import com.ecommOmar.ecomm.dto.orderProductResponseDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/customer")
public class reviewController {

	private final reviewService reviewService;

	@GetMapping("/ordered_products/{orderId}")
	public ResponseEntity<orderProductResponseDTO> getOrderedProductsDetailsByOrderId(@PathVariable Long orderId) {
		return ResponseEntity.ok(reviewService.getOrderedProductsDetailsByOrderId(orderId));
	}

	@PostMapping("/review")
	public ResponseEntity<?> giveReview(@ModelAttribute ReviewDto reviewDto) throws IOException {
		ReviewDto reviewDto1 = reviewService.giveReview(reviewDto);

		if (reviewDto1 == null) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Something went wrong");
		}

		return ResponseEntity.status(HttpStatus.CREATED).body(reviewDto1);
	}

}
