package com.demo.real_estate.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.demo.real_estate.model.Department;
import com.demo.real_estate.model.Employee;
import com.demo.real_estate.repository.DepartmentRepository;
import com.demo.real_estate.request.EmployeeRequest;
import com.demo.real_estate.service.EmployeeService;

import jakarta.validation.Valid;

@RestController // @Controller + @ResponseBody
@RequestMapping("department")
public class DepartmentController {
	
	@Autowired
	private DepartmentRepository dRepo;
	
	@GetMapping("/{id}")
	public ResponseEntity<Department> getEmployee(@PathVariable("id") Long id) {
		Optional<Department> departmentOps = dRepo.findById(id);
		Department department = new Department();
		if(departmentOps.isPresent()) {
			department = departmentOps.get();
			return  new ResponseEntity<Department>( department , HttpStatus.OK);
		}
		throw new RuntimeException("Department is not found for the id "+ id);
		
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<HttpStatus> deleteEmployee(@PathVariable Long id) {
		dRepo.deleteById(id);
		return new ResponseEntity<HttpStatus>(HttpStatus.NO_CONTENT);
	}
	
	
	
	
}
