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
import be.qde.spring.spending.api.entity.User;
import be.qde.spring.spending.api.repository.CategoryRepository;
import be.qde.spring.spending.api.repository.UserRepository;

@RestController
@RequestMapping("/categories/")
public class CategoryController extends AuthenticationController {

	@Autowired
	public CategoryRepository categoryRepository;
	
	@Autowired
	public UserRepository userRepository;
	
	@GetMapping()
	public List<Category> getAllCategories(){
		User owner = this.getAuthenticatedUser();
		return this.categoryRepository.findByOwner(owner);
	}
	
	@PostMapping()
	public Category addCategory(@RequestBody Category category) {
		category.setOwner(this.getAuthenticatedUser());
		return this.categoryRepository.save(category);
	}
	
	@PutMapping()
	public Category updateCategory(@RequestBody Category category) {
		category.setOwner(this.getAuthenticatedUser());
		return this.categoryRepository.save(category);
	}
	
	@DeleteMapping("{categoryId}")
	public void deleteCategory(@PathVariable Integer categoryId) {
		this.categoryRepository.delete(categoryId);
	}

}
