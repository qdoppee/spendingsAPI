package be.qde.spring.spending.api.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import be.qde.spring.spending.api.entity.Spending;
import be.qde.spring.spending.api.entity.User;
import be.qde.spring.spending.api.service.SpendingService;

@RestController
@RequestMapping("/spendings")
public class SpendingController extends AuthenticationController{

	@Autowired
	SpendingService spendingService;
	
	@GetMapping()
	public List<Spending> getAllSpendings(){
		User owner = this.getAuthenticatedUser();
		return this.spendingService.getAllSpendingsOfUser(owner);
	}
	
	@PostMapping("/categories/{categoryId}")
	public Spending addSpending(@RequestBody Spending spending, @PathVariable Integer categoryId) {
		return this.spendingService.addSpending(spending, categoryId);
	}
	
}