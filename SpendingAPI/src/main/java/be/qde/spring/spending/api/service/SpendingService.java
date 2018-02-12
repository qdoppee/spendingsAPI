package be.qde.spring.spending.api.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import be.qde.spring.spending.api.entity.Category;
import be.qde.spring.spending.api.entity.Spending;
import be.qde.spring.spending.api.entity.User;
import be.qde.spring.spending.api.repository.CategoryRepository;
import be.qde.spring.spending.api.repository.SpendingRepository;

@Service
public class SpendingService {

	@Autowired
	SpendingRepository spendingRepository;
	
	@Autowired
	CategoryRepository categoryRepository;
	
	public List<Spending> getAllSpendingsOfUser(User user){
		List<Category> categories = this.categoryRepository.findByOwner(user);
		return this.spendingRepository.findByCategoryIn(categories);
	}

	public Spending addSpending(Spending spending, Integer categoryId) {
		Category category = this.categoryRepository.getOne(categoryId);
		spending.setCategory(category);
		return this.spendingRepository.save(spending);
	}
	
}
