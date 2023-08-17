package com.demo.real_estate.controller;

import java.util.ArrayList;
import java.util.List;

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

import com.demo.real_estate.model.Address;
import com.demo.real_estate.model.Department;
import com.demo.real_estate.model.Employee;
import com.demo.real_estate.repository.AddressRepository;
import com.demo.real_estate.repository.DepartmentRepository;
import com.demo.real_estate.request.EmployeeRequest;
import com.demo.real_estate.service.EmployeeService;

import jakarta.validation.Valid;

@RestController // @Controller + @ResponseBody
@RequestMapping("employees")
public class EmployeeController {
	
	@Autowired
	private EmployeeService eService;
	
	@Autowired
	AddressRepository addRepo;
	
	@Value("${app.version}")
	private String appVersion;
	
	@GetMapping("/version")
	public String getAppDetails() {
		return appVersion;
	}
	
	@GetMapping()
	public ResponseEntity<List<Employee>> getEmployees(@RequestParam Integer pageNumber, @RequestParam Integer pageSize) {
		return new ResponseEntity<List<Employee>>(eService.getEmployees(pageNumber, pageSize), HttpStatus.OK);
	}
	// join
	@GetMapping("/filter/{name}")
	public ResponseEntity<List<Employee>> getEmployeesByDepartment( @PathVariable String name) {
		return new ResponseEntity<List<Employee>>(eService.getEmployeeByDepartmentName(name), HttpStatus.OK);
	}
	@GetMapping("/myfilter/{name}")
	public ResponseEntity<List<Employee>> getEmployeeByDeptName(@PathVariable String name){
		return new ResponseEntity<List<Employee>>(eService.getEmployeeByDeptName(name), HttpStatus.OK);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Employee> getEmployee(@PathVariable("id") Long id) {
		return  new ResponseEntity<Employee>( eService.getSingleEmployee(id), HttpStatus.OK);
	}
	
	
	@PostMapping()
	public ResponseEntity<Employee> saveEmployee(@Valid @RequestBody Employee employee) {
		// khởi tạo department
		System.out.println(employee.getAddress());
		return new ResponseEntity<Employee>(eService.saveEmployee(employee), HttpStatus.CREATED);
	}
	
	@DeleteMapping()
	public ResponseEntity<HttpStatus>  deleteEmployee(@RequestParam Long id) {
		eService.deleteEmployee(id);
		return new ResponseEntity<HttpStatus>(HttpStatus.NO_CONTENT);
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<Employee> updateEmployee(@PathVariable Long id,@RequestBody Employee employee) {
		employee.setId(id);
		return new ResponseEntity<Employee>(eService.updateEmployee(employee), HttpStatus.OK);
	}
	
	@GetMapping("/filterByName")
	public ResponseEntity<List<Employee>> getEmployeeByName(@RequestParam String name){
		return new ResponseEntity<List<Employee>>(eService.getEmployeeByName(name), HttpStatus.OK);
	}
	@GetMapping("/filterByNameAndLocation")
	public ResponseEntity<List<Employee>> getEmployeeByNameAndLocation(@RequestParam String name, @RequestParam String location){
		return new ResponseEntity<List<Employee>>(eService.getEmployeeByNameAndLocation(name, location), HttpStatus.OK);
	}
	
	
	
	
	
}
