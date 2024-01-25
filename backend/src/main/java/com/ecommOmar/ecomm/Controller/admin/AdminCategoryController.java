package com.ecommOmar.ecomm.Controller.admin;


import com.ecommOmar.ecomm.Entity.Category;
import com.ecommOmar.ecomm.Services.admin.category.CategoryService;
import com.ecommOmar.ecomm.dto.CategoryDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/admin")
@RequiredArgsConstructor
public class AdminCategoryController {


	private final CategoryService categoryService;


	@PostMapping("category")
	public ResponseEntity<Category> createCategory(@RequestBody CategoryDto categoryDto){

		Category category=categoryService.createCategory(categoryDto);

		return ResponseEntity.status(HttpStatus.CREATED).body(category);

	}

	@GetMapping("")
	public ResponseEntity<List<Category>> getAllCategories(){
		return ResponseEntity.ok(categoryService.getAllCategories());
	}
}
