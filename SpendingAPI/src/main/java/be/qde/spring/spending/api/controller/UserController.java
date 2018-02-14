package be.qde.spring.spending.api.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import be.qde.spring.spending.api.entity.User;
import be.qde.spring.spending.api.repository.UserRepository;

@RestController
@RequestMapping("/users/")
public class UserController extends AuthenticationController {
	
	@Autowired
	private BCryptPasswordEncoder passwordEncoder;
	
	@Autowired
	private UserRepository userRepository;
	
	@GetMapping
	public List<User> getUsers(){
		return this.userRepository.findAll();
	}
	
	@PostMapping()
	public User addUser(@RequestBody User user) {
		user.setPassword(this.passwordEncoder.encode(user.getPassword()));
		return this.userRepository.save(user);
	}
	
	@DeleteMapping("delete")
	public void deleteUser() {
		User user = this.getAuthenticatedUser();
		this.userRepository.delete(user);
	}
	
	@PutMapping
	public User updateUser(@RequestBody User user) {
		User authenticatedUser = this.getAuthenticatedUser();
		authenticatedUser.setEmail(user.getEmail());
		authenticatedUser.setUsername(user.getUsername());
		authenticatedUser.setPassword(passwordEncoder.encode(user.getPassword()));
		return this.userRepository.save(authenticatedUser);
	}
	
	@GetMapping("self")
	public User getUser() {
		return this.getAuthenticatedUser();
	}
	
	@GetMapping("{userId}")
	public User getUser(@PathVariable Integer userId) {
		return this.userRepository.findOne(userId);
	}

}
