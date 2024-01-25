package com.ecommOmar.ecomm.Services.Customer.Cart;


import com.ecommOmar.ecomm.Entity.*;
import com.ecommOmar.ecomm.Enums.OrderStatus;
import com.ecommOmar.ecomm.Filters.ValidationException;
import com.ecommOmar.ecomm.Repository.*;
import com.ecommOmar.ecomm.dto.AddProductInCardDto;
import com.ecommOmar.ecomm.dto.OrderDto;
import com.ecommOmar.ecomm.dto.PlaceOrderDto;
import com.ecommOmar.ecomm.dto.cartDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class CartServiceImpl implements CartService{


	@Autowired
	private OrderRepository orderRepository;

	@Autowired
	private userRepository userRepository ;


	@Autowired
	private CartItemRepository cartItemRepository;


	@Autowired
	private ProductRepository productRepository;
	@Autowired
	private CouponRepository couponRepository;




	public ResponseEntity<?>  addProductToCart(AddProductInCardDto addProductInCardDto){



		Order activeOrder = orderRepository.findByUserIdAndOrderStatus(
				addProductInCardDto.getUserId() , OrderStatus.Pending);

		Optional<CartItemes> optionalCartItems = cartItemRepository.
				findByProductIdAndOrderIdAndUserId(addProductInCardDto.getProductId(),
						activeOrder.getId(),addProductInCardDto.getUserId());


		if (optionalCartItems.isPresent()) {

			return ResponseEntity.status(HttpStatus.CONFLICT).body(null);

		}else {
			Optional<Product> optionalProduct =productRepository.findById(addProductInCardDto.getProductId());

			Optional<User> optionalUser =userRepository.findById(addProductInCardDto.getUserId());


			if (optionalUser.isPresent() && optionalProduct.isPresent()) {

				CartItemes cartItemes = new CartItemes() ;
				cartItemes.setProduct(optionalProduct.get());
				cartItemes.setPrice(optionalProduct.get().getPrice());
				cartItemes.setQuantity(1L);
				cartItemes.setUser(optionalUser.get());
				cartItemes.setOrder(activeOrder);

				CartItemes updatedcart = cartItemRepository.save(cartItemes);

				activeOrder.setTotalAmount(activeOrder.getTotalAmount()+cartItemes.getPrice());
				activeOrder.setTotalAmount(activeOrder.getAmount()+cartItemes.getPrice());
				activeOrder.getCartItems().add(cartItemes);


				orderRepository.save(activeOrder);

				return ResponseEntity.status(HttpStatus.CREATED).body(cartItemes);

			}else{
				return ResponseEntity.status(HttpStatus.NOT_FOUND).body("user ou product not found");
			}
		}

	}


	public OrderDto getCartByUserId(Long userId){

		Order activeOrder = orderRepository.findByUserIdAndOrderStatus(
				userId, OrderStatus.Pending);

		List<cartDto> cartDtoList = activeOrder.getCartItems().stream().
				map(CartItemes::getCartDto).collect(Collectors.toList());

		OrderDto orderDto = new OrderDto();
		orderDto.setAmount(activeOrder.getAmount());
		orderDto.setId(activeOrder.getId());
		orderDto.setOrderStatus(activeOrder.getOrderStatus());
		orderDto.setDiscount(activeOrder.getDiscount());
		orderDto.setTotalAmount(activeOrder.getTotalAmount());
		orderDto.setCartItems(cartDtoList);

		if (activeOrder.getCoupon()!= null) {
			orderDto.setCouponName(activeOrder.getCoupon().getName());
		}
		return orderDto;
	}


	public OrderDto applyCoupon(Long userId, String code) {

		Order activeOrder = orderRepository.findByUserIdAndOrderStatus(userId, OrderStatus.Pending);


		Coupon coupon = couponRepository.findByCode(code)
				.orElseThrow(() -> new ValidationException("Coupon not found."));


		if (couponIsExpired(coupon)) {
			throw new ValidationException("coupon is expired");
		}

		double discountAmount = (coupon.getDiscount() / 100.0) * activeOrder.getTotalAmount();
		double netAmount = activeOrder.getTotalAmount() - discountAmount;
		activeOrder.setAmount((long) netAmount);
		activeOrder.setDiscount((long) discountAmount);
		activeOrder.setCoupon(coupon);
		orderRepository.save(activeOrder);
		return activeOrder.getOrderDto(); // Assuming getOrderDto() returns the OrderDto for the activeOrder


	}

	private boolean couponIsExpired(Coupon coupon) {
		Date currentDate = new Date();
		Date expirationDate = coupon.getExpirationDate();

		return expirationDate != null && currentDate.after(expirationDate);
	}


	public OrderDto placeOrder(PlaceOrderDto placeOrderDto) {
		Order activeOrder = orderRepository.findByUserIdAndOrderStatus(placeOrderDto.getUserId(), OrderStatus.Pending);

		Optional<User> optionalUser = userRepository.findById(placeOrderDto.getUserId());

		if (optionalUser.isPresent()) {
			activeOrder.setOrderDescription(placeOrderDto.getOrderDescription());
			activeOrder.setAddress(placeOrderDto.getAddress());
			activeOrder.setDate(new Date());
			activeOrder.setOrderStatus(OrderStatus.Placed);
			activeOrder.setTrackingId(UUID.randomUUID());

			orderRepository.save(activeOrder);

			Order order = new Order();
			order.setAmount(0L);
			order.setTotalAmount(0L);
			order.setDiscount(0L);
			order.setUser(optionalUser.get());
			order.setOrderStatus(OrderStatus.Pending);

			orderRepository.save(order);

			return activeOrder.getOrderDto();
		}

		// Assuming OrderDto is a class representing the data transfer object for Order
		return null;
	}


	public List<OrderDto> getMyPlacedOrders(Long userId) {
		return orderRepository.findByUserIdAndOrderStatusIn(
						userId,
						List.of(OrderStatus.Placed, OrderStatus.Shipped, OrderStatus.Delivered)
				)
				.stream()
				.map(Order::getOrderDto)
				.collect(Collectors.toList());
	}


	public OrderDto searchOrderByTrackingId(UUID trackingId) {
		Optional<Order> optionalOrder = orderRepository.findByTrackingId(trackingId);

		if (optionalOrder.isPresent()) {
			return optionalOrder.get().getOrderDto();
		}

		return null;
	}





}
