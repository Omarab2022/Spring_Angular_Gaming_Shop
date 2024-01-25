package com.ecommOmar.ecomm.Services.admin.adminOrder;

import com.ecommOmar.ecomm.dto.AnalyticsResponse;
import com.ecommOmar.ecomm.dto.OrderDto;

import java.util.List;

public interface adminOrderService {

	List<OrderDto> getAllPlacedOrders();


	OrderDto changeOrderStatus(Long orderId, String status);

	AnalyticsResponse calculateAnalytics();
}
