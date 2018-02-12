package be.qde.spring.spending.api.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import be.qde.spring.spending.api.entity.User;
import be.qde.spring.spending.api.repository.UserRepository;

@RestController
@RequestMapping("/users/")
public class UserController {
	
	@Autowired
	private BCryptPasswordEncoder passwordEncoder;
	
	@Autowired
	private UserRepository userRepository;
	
	@GetMapping
	public List<User> getUsers(){
		return this.userRepository.findAll();
	}
	
	@PostMapping
	public User addUser(@RequestBody User user) {
		return this.userRepository.save(user);
	}
	
	@GetMapping("self")
	public User getUser() {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		String username = authentication.getName();
		return this.userRepository.findByUsername(username);
	}

	@PostMapping("sign-up")
	public void signUp(@RequestBody User user) {
		user.setPassword(passwordEncoder.encode(user.getPassword()));
		this.userRepository.save(user);
	}
}
