package com.demo.real_estate.service;

import java.util.List;

import com.demo.real_estate.model.Employee;

public interface EmployeeService {
	List<Employee> getEmployees(Integer pageNumber, Integer pageSize);
	Employee saveEmployee(Employee employee);
	Employee getSingleEmployee(Long id);
	void deleteEmployee (Long id);
	Employee updateEmployee(Employee employee);	
	List<Employee> getEmployeeByName(String name);
	List<Employee> getEmployeeByNameAndLocation(String name, String location);
	
	List<Employee> getEmployeeByDepartmentName(String name);
	
	List<Employee> getEmployeeByDeptName(String name);
	
	

}
