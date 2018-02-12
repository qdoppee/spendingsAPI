package be.qde.spring.spending.api.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

import be.qde.spring.spending.api.entity.User;
import be.qde.spring.spending.api.repository.UserRepository;

public abstract class AuthenticationController {

	@Autowired
	private UserRepository userRepository;
	
	protected User getAuthenticatedUser() {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		String username = authentication.getName();
		return this.userRepository.findByUsername(username);
	}
	
}
