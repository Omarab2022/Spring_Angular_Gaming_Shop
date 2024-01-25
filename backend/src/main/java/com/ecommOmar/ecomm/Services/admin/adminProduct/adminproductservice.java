package com.ecommOmar.ecomm.Services.admin.adminProduct;

import com.ecommOmar.ecomm.dto.ProductDto;

import java.io.IOException;
import java.util.List;

public interface adminproductservice {


	ProductDto addProduct(ProductDto productDto) throws IOException;

	List<ProductDto> getAllProducts();


	List<ProductDto> getAllProductByName(String name);


	boolean deleteProduct(Long id) ;

	ProductDto getProductById(Long productId);


	ProductDto updateProduct(Long productId, ProductDto productDto) throws  IOException;
}
