package com.ecommOmar.ecomm.Controller.admin;


import com.ecommOmar.ecomm.Services.admin.adminOrder.adminOrderImpl;
import com.ecommOmar.ecomm.dto.AnalyticsResponse;
import com.ecommOmar.ecomm.dto.OrderDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.ecommOmar.ecomm.Services.admin.adminOrder.adminOrderService;
import java.util.List;

@RestController
@RequestMapping("/api/admin")
@RequiredArgsConstructor
public class adminOrderController {


	private final adminOrderImpl adminOrder ;

	private final adminOrderService adminOrderService;

	@GetMapping("/placedOrders")
	public ResponseEntity<List<OrderDto>> getAllPlacedOrders() {

		return  ResponseEntity.ok(adminOrder.getAllPlacedOrders());
	}



	@GetMapping("/order/{orderId}/{status}")
	public ResponseEntity<?> changeOrderStatus(@PathVariable Long orderId, @PathVariable String status) {
		OrderDto orderDto = adminOrder.changeOrderStatus(orderId, status);

		if (orderDto == null) {
			return new ResponseEntity<>("Something went wrong!" , HttpStatus.BAD_REQUEST);
		}

		return ResponseEntity.status(HttpStatus.OK).body(orderDto);
	}


	@GetMapping("/order/analytics")
	public ResponseEntity<AnalyticsResponse> getAnalytics() {
		AnalyticsResponse analyticsResponse = adminOrderService.calculateAnalytics();
		return ResponseEntity.ok(analyticsResponse);
	}
}
