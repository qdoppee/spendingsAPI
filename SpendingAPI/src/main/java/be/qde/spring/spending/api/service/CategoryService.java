package be.qde.spring.spending.api.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;

import be.qde.spring.spending.api.entity.Category;
import be.qde.spring.spending.api.entity.User;
import be.qde.spring.spending.api.repository.CategoryRepository;
import be.qde.spring.spending.api.repository.SpendingRepository;

public class CategoryService {

	@Autowired
	CategoryRepository categoryRepository;
	
	@Autowired
	SpendingRepository spendingRepository;
	
	public Category createCategory(Category category, User user) {
		category.setOwner(user);
		return this.categoryRepository.save(category);
	}
	
	public Category readCategory(Integer categoryId) {
		return this.categoryRepository.getOne(categoryId);
	}
	
	public List<Category> readCategory(User user) {
		return this.categoryRepository.findByOwner(user);
	}
	
	
	public Category updateCategory(Category category, User user) {
		category.setOwner(user);
		return this.categoryRepository.save(category);
	}
	
	@Transactional
	public void deleteCategory(Category category) {
		this.spendingRepository.deleteByCategory(category);
		this.categoryRepository.delete(category);
	}

	@Transactional
	public void deleteCategory(Integer categoryId) {
		Category category = this.categoryRepository.getOne(categoryId);
		this.spendingRepository.deleteByCategory(category);
		this.categoryRepository.delete(category);
	}
	
	
}
