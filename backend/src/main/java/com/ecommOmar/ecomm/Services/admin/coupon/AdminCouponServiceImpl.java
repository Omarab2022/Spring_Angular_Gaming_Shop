package com.ecommOmar.ecomm.Services.admin.coupon;


import com.ecommOmar.ecomm.Entity.Coupon;
import com.ecommOmar.ecomm.Filters.ValidationException;
import com.ecommOmar.ecomm.Repository.CouponRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AdminCouponServiceImpl implements AdminCouponService{

	private final CouponRepository couponRepository ;

	public Coupon createCoupon (Coupon coupon){

		if (couponRepository.existsByCode(coupon.getCode())) {

			throw new ValidationException("coupon code already exist !!");
			
		}

		return couponRepository.save(coupon);
	}

	public List<Coupon> getAllCoupons(){
		return couponRepository.findAll();
	}


}
