package com.ecommOmar.ecomm.Services.admin.FAQ;

import com.ecommOmar.ecomm.Entity.FAQ;
import com.ecommOmar.ecomm.Entity.Product;
import com.ecommOmar.ecomm.Repository.FAQRepository;
import com.ecommOmar.ecomm.Repository.ProductRepository;
import com.ecommOmar.ecomm.dto.FAQdto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class FAQservicIpl implements FAQservice{


	private final FAQRepository faqRepository ;

	private final ProductRepository productRepository;

	public FAQdto postFAQ(Long productId, FAQdto faqDto) {

		Optional<Product> optionalProduct = productRepository.findById(productId);

		if (optionalProduct.isPresent()) {

			FAQ faq = new FAQ();

			faq.setQuestion(faqDto.getQuestion());
			faq.setAnswer(faqDto.getAnswer());

			faq.setProduct(optionalProduct.get());


			return faqRepository.save(faq).getFAQDto();
		} else {

			return null;
		}
	}
}
