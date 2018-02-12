package be.qde.spring.spending.api.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import be.qde.spring.spending.api.entity.User;

public interface UserRepository extends JpaRepository<User, Integer>{

	public User findByUsername(String username);
}
