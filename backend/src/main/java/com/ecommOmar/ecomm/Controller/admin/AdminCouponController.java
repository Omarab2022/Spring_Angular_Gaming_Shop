package com.ecommOmar.ecomm.Controller.admin;


import com.ecommOmar.ecomm.Entity.Coupon;
import com.ecommOmar.ecomm.Filters.ValidationException;
import com.ecommOmar.ecomm.Services.admin.coupon.AdminCouponService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/admin/coupons")
@RequiredArgsConstructor
public class AdminCouponController {


	private final AdminCouponService adminCouponService ;


	@PostMapping
	public ResponseEntity<?> createCoupon(@RequestBody Coupon coupon){
		try {
			Coupon createdcoupon = adminCouponService.createCoupon(coupon);

			return ResponseEntity.ok(createdcoupon);
		}catch (ValidationException ex){
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ex.getMessage());
		}
	}


	@GetMapping
	private ResponseEntity<List<Coupon>> getAllCoupons(){
		return ResponseEntity.ok(adminCouponService.getAllCoupons());
	}
}
