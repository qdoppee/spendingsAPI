package be.qde.spring.spending.api.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import be.qde.spring.spending.api.entity.Category;
import be.qde.spring.spending.api.entity.User;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Integer>{
	
	public List<Category> findByOwner(User owner);
	
}
