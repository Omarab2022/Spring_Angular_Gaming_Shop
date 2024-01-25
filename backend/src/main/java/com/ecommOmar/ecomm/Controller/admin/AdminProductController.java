package com.ecommOmar.ecomm.Controller.admin;


import com.ecommOmar.ecomm.Entity.Product;
import com.ecommOmar.ecomm.Repository.ProductRepository;
import com.ecommOmar.ecomm.Services.admin.FAQ.FAQservice;
import com.ecommOmar.ecomm.Services.admin.adminProduct.adminproductservice;
import com.ecommOmar.ecomm.dto.FAQdto;
import com.ecommOmar.ecomm.dto.ProductDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/admin")
@RequiredArgsConstructor
public class AdminProductController {


	private final adminproductservice adminProductService ;


	private final FAQservice faQservice;


	@PostMapping("/product")
	public ResponseEntity<ProductDto> addProduct(@ModelAttribute ProductDto productDto) throws  IOException {
		ProductDto ProductDto1 = adminProductService.addProduct(productDto);
		return ResponseEntity.status(HttpStatus.CREATED).body(ProductDto1);
	}

	@GetMapping("/products")
	public ResponseEntity<List<ProductDto>> getAllProducts() {
		List<ProductDto> productDtos = adminProductService.getAllProducts();
		return ResponseEntity.ok(productDtos);
	}


	@GetMapping("/search/{name}")
	public ResponseEntity<List<ProductDto>> getAllProductByName(@PathVariable String name) {
		List<ProductDto> productDtos = adminProductService.getAllProductByName(name);
		return ResponseEntity.ok(productDtos);
	}


	@DeleteMapping("/product/{productId}")
	public ResponseEntity<Void>  deleteProduct(@PathVariable Long productId){

		boolean deleted = adminProductService.deleteProduct(productId);

		if(deleted){
			return ResponseEntity.noContent().build();
		}

		return ResponseEntity.notFound().build();
	}


	@PostMapping("/faq/{productId}")
	public ResponseEntity<FAQdto> postFAQ(@PathVariable Long productId, @RequestBody FAQdto faqDto) {
		return ResponseEntity.status(HttpStatus.CREATED).body(faQservice.postFAQ(productId, faqDto));
	}


	@GetMapping("/product/{productId}")
	public ResponseEntity<ProductDto> getProductById(@PathVariable Long productId) {
		ProductDto productDto = adminProductService.getProductById(productId);

		if (productDto != null) {
			return ResponseEntity.ok(productDto);
		} else {
			return ResponseEntity.notFound().build();
		}
	}


	@PutMapping("/product/{productId}")
	public ResponseEntity<ProductDto> updateProduct(@PathVariable Long productId, @ModelAttribute ProductDto productDto) throws IOException {
		ProductDto updatedProduct = adminProductService.updateProduct(productId, productDto);

		if (updatedProduct != null) {
			return ResponseEntity.ok(updatedProduct);
		} else {
			return ResponseEntity.notFound().build();
		}
	}



}
