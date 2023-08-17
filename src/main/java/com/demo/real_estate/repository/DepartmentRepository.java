package com.demo.real_estate.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.demo.real_estate.model.Department;

@Repository
public interface DepartmentRepository extends JpaRepository<Department, Long> {
	
}
