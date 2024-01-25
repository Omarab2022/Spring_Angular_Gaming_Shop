package com.ecommOmar.ecomm.Controller.customer;


import com.ecommOmar.ecomm.Filters.ValidationException;
import com.ecommOmar.ecomm.Services.Customer.Cart.CartService;
import com.ecommOmar.ecomm.dto.AddProductInCardDto;
import com.ecommOmar.ecomm.dto.OrderDto;
import com.ecommOmar.ecomm.dto.PlaceOrderDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/customer")
@RequiredArgsConstructor
public class CartController {


	private  final CartService cartService ;



	@PostMapping("/cart")
	public ResponseEntity<?>  addProductToCart(@RequestBody AddProductInCardDto addProductInCardDto){

		return cartService.addProductToCart(addProductInCardDto);
	}


	@GetMapping("/cart/{userId}")
	public ResponseEntity<?>  getCartByUserId(@PathVariable Long userId){

		OrderDto orderDto = cartService.getCartByUserId(userId);

		return ResponseEntity.status(HttpStatus.OK).body(orderDto);
	}


	@GetMapping("/coupon/{userId}/{code}")
	public ResponseEntity<?> applyCoupon(@PathVariable Long userId, @PathVariable String code) {
		try {
			OrderDto orderDto = cartService.applyCoupon(userId, code);
			return ResponseEntity.ok(orderDto);
		} catch (ValidationException ex) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ex.getMessage());
		}
	}



	@PostMapping("/placeOrder")
	public ResponseEntity<OrderDto> placeOrder(@RequestBody PlaceOrderDto placeOrderDto) {
		return ResponseEntity.status(HttpStatus.CREATED).body(cartService.placeOrder(placeOrderDto));
	}

	@GetMapping("/myOrders/{userId}")
	public ResponseEntity<List<OrderDto>> getMyPlacedOrders(@PathVariable Long userId) {
		return ResponseEntity.ok(cartService.getMyPlacedOrders(userId));
	}


}
