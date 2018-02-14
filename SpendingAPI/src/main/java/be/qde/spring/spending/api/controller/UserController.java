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

import be.qde.spring.spending.api.entity.User;
import be.qde.spring.spending.api.service.UserService;

@RestController
@RequestMapping("/users/")
public class UserController extends AuthenticationController {
	
	@Autowired
	UserService userService;
	
	@GetMapping
	public List<User> getUsers(){
		return this.userService.readUser();
	}
	
	@PostMapping()
	public User addUser(@RequestBody User user) {
		return this.userService.createUser(user);
	}
	
	@PutMapping()
	public User updateUser(@RequestBody User user) {
		return this.userService.updateUser(user);
	}
	
	@DeleteMapping("delete")
	public void deleteUser() {
		this.userService.deleteUser(this.getAuthenticatedUser());
	}
	
	@GetMapping("self")
	public User getUser() {
		return this.getAuthenticatedUser();
	}
	
	@GetMapping("{userId}")
	public User getUser(@PathVariable Integer userId) {
		return this.userService.readUser(userId);
	}
	
	

}
