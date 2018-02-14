package be.qde.spring.spending.api.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import be.qde.spring.spending.api.entity.Category;
import be.qde.spring.spending.api.service.CategoryService;

@RestController
@RequestMapping("/categories/")
public class CategoryController extends AuthenticationController {

	@Autowired
	public CategoryService categoryService;
	
	
	@GetMapping()
	public List<Category> getAllCategories(){
		return this.categoryService.readCategory(this.getAuthenticatedUser());
	}
	
	@PostMapping()
	public Category addCategory(@RequestBody Category category) {
		return this.categoryService.createCategory(category, this.getAuthenticatedUser());
	}
	
	@PutMapping()
	public Category updateCategory(@RequestBody Category category) {
		return this.categoryService.updateCategory(category, this.getAuthenticatedUser());
	}
	
	@DeleteMapping("{categoryId}")
	public void deleteCategory(@PathVariable Integer categoryId) {
		this.categoryService.deleteCategory(categoryId);
	}
}
