package com.ecommOmar.ecomm.Controller.customer;


import com.ecommOmar.ecomm.Services.Customer.CustomerProductService;
import com.ecommOmar.ecomm.dto.ProductDetailsDto;
import com.ecommOmar.ecomm.dto.ProductDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/customer")
@RequiredArgsConstructor
public class CustomerProductController {


	private final CustomerProductService customerProductService ;


	@GetMapping("/products")
	public ResponseEntity<List<ProductDto>> getAllProducts() {
		List<ProductDto> productDtos = customerProductService.getAllProducts();
		return ResponseEntity.ok(productDtos);
	}


	@GetMapping("/search/{name}")
	public ResponseEntity<List<ProductDto>> getAllProductByName(@PathVariable String name) {
		List<ProductDto> productDtos = customerProductService.searchProductBytitle(name);
		return ResponseEntity.ok(productDtos);
	}

	@GetMapping("/product/{productId}")
	public ResponseEntity<ProductDetailsDto> getProductDetailById(@PathVariable Long productId) {
		ProductDetailsDto productDetailDto = customerProductService.getProductDetailById(productId);

		if (productDetailDto == null) {
			return ResponseEntity.notFound().build();
		}

		return ResponseEntity.ok(productDetailDto);
	}





}
