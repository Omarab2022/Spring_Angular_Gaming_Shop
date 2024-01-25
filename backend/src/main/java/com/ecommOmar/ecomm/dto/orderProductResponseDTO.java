package com.ecommOmar.ecomm.dto;


import lombok.Data;

import java.util.List;

@Data
public class orderProductResponseDTO {

	private List<ProductDto> productDtoList ;

	private Long orderAmount ;
}
