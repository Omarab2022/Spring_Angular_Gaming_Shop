package com.ecommOmar.ecomm.Services.admin.coupon;

import com.ecommOmar.ecomm.Entity.Coupon;

import java.util.List;

public interface AdminCouponService {


	Coupon createCoupon (Coupon coupon);

	 List<Coupon> getAllCoupons() ;
}
