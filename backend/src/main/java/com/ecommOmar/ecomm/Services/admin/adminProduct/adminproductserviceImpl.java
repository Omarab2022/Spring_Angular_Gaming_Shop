package com.ecommOmar.ecomm.Services.admin.adminProduct;


import com.ecommOmar.ecomm.Entity.Category;
import com.ecommOmar.ecomm.Entity.Product;
import com.ecommOmar.ecomm.Repository.CategoryRepository;
import com.ecommOmar.ecomm.Repository.ProductRepository;
import com.ecommOmar.ecomm.dto.ProductDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class adminproductserviceImpl implements adminproductservice{


	private final ProductRepository productRepository ;

	private final CategoryRepository categoryRepository;


	public ProductDto addProduct(ProductDto productDto) throws IOException {
		Product product = new Product();
		product.setName(productDto.getName());
		product.setDescription(productDto.getDescription());
		product.setPrice(productDto.getPrice());
        product.setImg(productDto.getImg().getBytes());

		Category category = categoryRepository.findById(productDto.getCategoryId())
				.orElseThrow();

		product.setCategory(category);

		return productRepository.save(product).getDto();


	}

	public List<ProductDto> getAllProducts() {
		List<Product> products = productRepository.findAll();
		return products.stream()
				.map(Product::getDto) // Assuming you have a getDto method in your Product entity
				.collect(Collectors.toList());
	}


	public List<ProductDto> getAllProductByName(String name) {
		List<Product> products = productRepository.findAllByNameContaining(name);
		return products.stream()
				.map(Product::getDto) // Assuming you have a getDto method in your Product entity
				.collect(Collectors.toList());
	}


	public boolean deleteProduct(Long id){

		Optional<Product> optionalProduct = productRepository.findById(id);

		if(optionalProduct.isPresent()){
			productRepository.deleteById(id);

			return true;
		}
		return false;
	}


	public ProductDto getProductById(Long productId) {
		Optional<Product> optionalProduct = productRepository.findById(productId);

		if (optionalProduct.isPresent()) {
			return optionalProduct.get().getDto();
		} else {
			return null; // You might want to consider throwing an exception or returning a specific response based on your use case
		}
	}


	public ProductDto updateProduct(Long productId, ProductDto productDto) throws IOException {
		Optional<Product> optionalProduct = productRepository.findById(productId);
		Optional<Category> optionalCategory = categoryRepository.findById(productDto.getCategoryId());

		if (optionalProduct.isPresent() && optionalCategory.isPresent()) {
			Product product = optionalProduct.get();
			product.setName(productDto.getName());
			product.setPrice(productDto.getPrice());
			product.setDescription(productDto.getDescription());
			product.setCategory(optionalCategory.get());

			if (productDto.getImg() != null) {

				product.setImg(productDto.getImg().getBytes());
			}

			return productRepository.save(product).getDto();
		} else {
			return null;
		}
	}

}
