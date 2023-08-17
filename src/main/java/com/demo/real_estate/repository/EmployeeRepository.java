package com.demo.real_estate.repository;

import java.util.List;

import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.demo.real_estate.model.Employee;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Long> {
	// findBy strict named
	List<Employee> findByName(String name);
	// select * from table where name = 'lthaibinh' and localtion='vietnam'
	List<Employee> findByNameAndLocation(String name, String location, Sort sort);
	
	@Query("From Employee where name = :name Or location = :location")
	List<Employee> getByNameAndLocation(@Param("name") String name, String location);
	
	List<Employee> findByDepartmentName(String name);
	// JPQL join query in data jpa
	
	@Query("From Employee where department.name = :name")
	List<Employee> getEmployeeByDeptName(String name);
}
