package com.ecommOmar.ecomm.Services.Customer;

import com.ecommOmar.ecomm.dto.ProductDetailsDto;
import com.ecommOmar.ecomm.dto.ProductDto;

import java.util.List;

public interface CustomerProductService {

	List<ProductDto> getAllProducts();


	List<ProductDto> searchProductBytitle(String title);


	public ProductDetailsDto getProductDetailById(Long productId);

}
