package com.ecommOmar.ecomm.Services.admin.FAQ;

import com.ecommOmar.ecomm.dto.FAQdto;

public interface FAQservice {

	FAQdto postFAQ(Long productId, FAQdto faqDto);
}
