package com.demo.real_estate.repository;

import java.util.List;

import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import com.demo.real_estate.model.Employee;

@Repository
public interface EmployeePaginationRepository extends PagingAndSortingRepository<Employee, Long> {
	List<Employee> findByName(String name);
	// select * from table where name = 'lthaibinh' and localtion='vietnam'
	List<Employee> findByNameAndLocation(String name, String location, Sort sort);
}
