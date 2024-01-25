package com.ecommOmar.ecomm.Services.Customer;


import com.ecommOmar.ecomm.Entity.FAQ;
import com.ecommOmar.ecomm.Entity.Product;
import com.ecommOmar.ecomm.Entity.Review;
import com.ecommOmar.ecomm.Repository.FAQRepository;
import com.ecommOmar.ecomm.Repository.ProductRepository;
import com.ecommOmar.ecomm.Repository.ReviewRepository;
import com.ecommOmar.ecomm.dto.ProductDetailsDto;
import com.ecommOmar.ecomm.dto.ProductDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CustomerProductServiceImpl implements CustomerProductService{

	private final ProductRepository productRepository ;

	private final FAQRepository faqRepository ;

	private final ReviewRepository reviewRepository ;

	public List<ProductDto> getAllProducts() {
		List<Product> products = productRepository.findAll();
		return products.stream()
				.map(Product::getDto) // Assuming you have a getDto method in your Product entity
				.collect(Collectors.toList());
	}


	public List<ProductDto> searchProductBytitle(String name) {
		List<Product> products = productRepository.findAllByNameContaining(name);
		return products.stream()
				.map(Product::getDto) // Assuming you have a getDto method in your Product entity
				.collect(Collectors.toList());
	}


	public ProductDetailsDto getProductDetailById(Long productId) {
		Optional<Product> optionalProduct = productRepository.findById(productId);

		if (optionalProduct.isPresent()) {
			List<FAQ> faqList = faqRepository.findAllByProductId(productId);
			List<Review> reviewsList = reviewRepository.findAllByProductId(productId);

			ProductDetailsDto productDetailDto = new ProductDetailsDto();
			productDetailDto.setProductDto(optionalProduct.get().getDto());
			productDetailDto.setFaQdtoList(faqList.stream().map(FAQ::getFAQDto).collect(Collectors.toList()));
			productDetailDto.setReviewDtoList(reviewsList.stream().map(Review::getDto).collect(Collectors.toList()));

			return productDetailDto;
		}

		return null;
	}



}
