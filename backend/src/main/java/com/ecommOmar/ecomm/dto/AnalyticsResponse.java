package com.ecommOmar.ecomm.dto;


import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class AnalyticsResponse {

	private Long Placed ;

	private Long shipped;
	private Long delivered;
// No usages

	private Long currentMonthOrders;
	private Long previousMonthOrders;

	private Long currentMonthEarnings;
	private Long previousMonthEarnings;

}
