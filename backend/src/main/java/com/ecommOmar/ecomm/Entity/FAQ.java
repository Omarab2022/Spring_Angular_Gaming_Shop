package com.ecommOmar.ecomm.Entity;


import com.ecommOmar.ecomm.dto.FAQdto;
import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

@Entity
@Data
public class FAQ {


	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id ;

	private String answer ;

	private String question ;

	@ManyToOne(fetch = FetchType.LAZY , optional = false)
	@JoinColumn(name = "product_id" , nullable = false)
	@OnDelete(action = OnDeleteAction.CASCADE)
	private Product product ;

	public FAQdto getFAQDto() {
		FAQdto faqDto = new FAQdto();
		faqDto.setId(id);
		faqDto.setQuestion(question);
		faqDto.setAnswer(answer); // Corrected from "faqDto.setAnswer(question);"
		faqDto.setProductID(product.getId());
		return faqDto;
	}

}
