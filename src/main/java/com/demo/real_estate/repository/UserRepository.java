package com.demo.real_estate.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.demo.real_estate.model.Address;
import com.demo.real_estate.model.Department;
import com.demo.real_estate.model.user.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
	//Optional<User> findByEmail(String email);
	Optional<User> findByUsername(String username);
}
