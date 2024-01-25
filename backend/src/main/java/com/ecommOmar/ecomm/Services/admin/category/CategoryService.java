package com.ecommOmar.ecomm.Services.admin.category;

import com.ecommOmar.ecomm.Entity.Category;
import com.ecommOmar.ecomm.dto.CategoryDto;

import java.util.List;

public interface CategoryService  {

	Category createCategory(CategoryDto categoryDto);

	List<Category> getAllCategories();
}
