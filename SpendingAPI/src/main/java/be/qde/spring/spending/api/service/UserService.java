package be.qde.spring.spending.api.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import be.qde.spring.spending.api.entity.Category;
import be.qde.spring.spending.api.entity.User;
import be.qde.spring.spending.api.repository.CategoryRepository;
import be.qde.spring.spending.api.repository.SpendingRepository;
import be.qde.spring.spending.api.repository.UserRepository;

@Service
public class UserService {

	@Autowired
	private BCryptPasswordEncoder passwordEncoder;
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private SpendingRepository spendingRepository;
	
	@Autowired
	private CategoryRepository categoryRepository;
	
	public User createUser(User user) {
		// Encode user's password
		String encodedPassword = this.passwordEncoder.encode(user.getPassword());
		user.setPassword(encodedPassword);
		return this.userRepository.save(user);
	}

	@Transactional
	public void deleteUser(User user) {
		// Delete the user's spendings
		List<Category> categories = this.categoryRepository.findByOwner(user);
		this.spendingRepository.deleteByCategoryIn(categories);	
		// Delete the users's category
		this.categoryRepository.delete(categories);
		// Delete the user
		this.userRepository.delete(user);
	}

	public User readUser(Integer userId) {
		return this.userRepository.getOne(userId);
	}

	public List<User> readUser() {
		return this.userRepository.findAll();
	}

	public User updateUser(User user) {
		User sourceUser = this.userRepository.getOne(user.getId());
		
		// If no new password, use existing-one
		if(user.getPassword() == null || user.getPassword() == "")
			user.setPassword(sourceUser.getPassword());

		// Else encode the new password
		else if(!user.getPassword().equals(sourceUser.getPassword()))
			user.setPassword(this.passwordEncoder.encode(user.getPassword()));
		
		return this.userRepository.save(user);
	}
	
}
