package com.ecommOmar.ecomm.Services.Customer.Cart;

import com.ecommOmar.ecomm.dto.AddProductInCardDto;
import com.ecommOmar.ecomm.dto.OrderDto;
import com.ecommOmar.ecomm.dto.PlaceOrderDto;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.UUID;

public interface CartService {

	ResponseEntity<?> addProductToCart(AddProductInCardDto addProductInCardDto);


	OrderDto getCartByUserId(Long userId);

	OrderDto applyCoupon(Long userId, String code);


	OrderDto placeOrder(PlaceOrderDto placeOrderDto);

	List<OrderDto> getMyPlacedOrders(Long userId) ;

	OrderDto searchOrderByTrackingId(UUID trackingId);
}
