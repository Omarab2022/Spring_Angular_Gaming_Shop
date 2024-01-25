package com.ecommOmar.ecomm.Config;


import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import com.ecommOmar.ecomm.Filters.jwtrequestfilter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@EnableWebSecurity
@Configuration
@RequiredArgsConstructor
public class WebSecurityConfiguration {

	private final jwtrequestfilter authfilter;


	@Bean
	public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {


		return http
				.csrf()
				.disable()
				.authorizeHttpRequests()
				.requestMatchers("/authenticate" , "/sign_up","/order/**" ,
						"/api/admin/category" , "/api/admin" , "/api/admin/product",
						"/api/admin/products" , "/api/admin/search/{name}",
						"/api/admin/product/{productId}",
						"/api/customer/products",
						"/api/customer/search/{name}",
						"/api/customer/cart",
						"/api/customer/cart/{userId}",
						"/api/admin/coupons",
						"/api/customer/coupon/{userId}/{code}",
						"/api/customer/placeOrder",
						"/api/admin/placedOrders" ,
						"/api/customer/order/{orderId}/{status}",
						"/api/customer/myOrders/{userId}",
						"/api/admin/faq/{productId}",
						"/api/admin/product/{productId}",
						"/api/customer/ordered_products/{orderId}",
						"/api/customer/review",
						"/api/customer/product/{productId}",
						"/api/customer/wishlist",
						"/api/customer/wishlist/{userId}",
						"/order/{trackingId}",
						"/api/admin/order/analytics")
				.permitAll().and()
				.authorizeHttpRequests()
				.requestMatchers("/api/**")
				.authenticated()
				.and()
				.sessionManagement()
				.sessionCreationPolicy(SessionCreationPolicy.STATELESS)
				.and()
				.addFilterBefore(authfilter , UsernamePasswordAuthenticationFilter.class)
				.build();

	}

	@Bean
	public PasswordEncoder passwordEncoder(){
		return new BCryptPasswordEncoder();
	}

	@Bean
	public AuthenticationManager authenticationManager(AuthenticationConfiguration configuration) throws Exception {
		return configuration.getAuthenticationManager();
	}
}
