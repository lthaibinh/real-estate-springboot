package com.demo.real_estate.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import com.demo.real_estate.model.Department;
import com.demo.real_estate.model.Employee;
import com.demo.real_estate.repository.EmployeePaginationRepository;
import com.demo.real_estate.repository.EmployeeRepository;

@Service
public class EmployeeServiceImpl implements EmployeeService {
	@Autowired
	private EmployeePaginationRepository ePaginationRepository;
	
	@Autowired
	private EmployeeRepository eRepository;
	
	@Override
	public List<Employee> getEmployees(Integer pageNumber, Integer pageSize) {
		
		//return eRepository.findAll();
		Pageable pages = PageRequest.of(pageNumber, pageSize, Direction.DESC, "id", "name");
		return ePaginationRepository.findAll(pages).getContent();
	}

	@Override
	public Employee saveEmployee(Employee employee) {
		
		return eRepository.save(employee);
	}
	@Override
	public Employee getSingleEmployee(Long id) {
		
		Optional<Employee> employee = eRepository.findById(id);
		if(employee.isPresent()) {
			return employee.get();
		}
		throw new RuntimeException("Employee is not found for the id "+ id);
	}
	@Override
	public void deleteEmployee(Long id) {
		
		eRepository.deleteById(id);
	}
	@Override
	public Employee updateEmployee(Employee employee) {
		// TODO Auto-generated method stub
		Optional<Employee> employee2 = eRepository.findById(employee.getId());
		if(employee2.isPresent()) {
			return eRepository.save(employee);
		}
		return null;
	}
	
	@Override
	public List<Employee> getEmployeeByName(String name) {
		return eRepository.findByName(name);
	}

	@Override
	public List<Employee> getEmployeeByNameAndLocation(String name, String location) {
		Sort sort = Sort.by(Sort.Direction.ASC, "id");
		return eRepository.getByNameAndLocation(name, location);
	}
	
	@Override
	public List<Employee> getEmployeeByDepartmentName(String name) {
		// TODO Auto-generated method stub
		return eRepository.findByDepartmentName(name);
	}
	@Override
	public List<Employee> getEmployeeByDeptName(String name) {
		return eRepository.getEmployeeByDeptName(name);
	}
}
