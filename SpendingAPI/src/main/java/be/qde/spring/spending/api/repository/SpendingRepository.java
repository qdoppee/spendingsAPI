package be.qde.spring.spending.api.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import be.qde.spring.spending.api.entity.Category;
import be.qde.spring.spending.api.entity.Spending;

@Repository
public interface SpendingRepository extends JpaRepository<Spending, Integer>{

	public List<Spending> findByCategoryIn(List<Category> categories);
	
	public void deleteByCategoryIn(List<Category> categories);

	public void deleteByCategory(Category category);
}
