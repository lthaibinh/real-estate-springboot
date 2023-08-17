package com.demo.real_estate.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.web.servletapi.SecurityContextHolderAwareRequestWrapper;
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

import com.demo.real_estate.dto.UserCreationDTO;
import com.demo.real_estate.dto.UserDTO;
import com.demo.real_estate.dto.UserUpdateDTO;
import com.demo.real_estate.model.Address;
import com.demo.real_estate.model.Employee;
import com.demo.real_estate.model.post.CategoryOfRealEstate;
import com.demo.real_estate.model.post.Post;
import com.demo.real_estate.model.user.User;
import com.demo.real_estate.repository.AddressRepository;
import com.demo.real_estate.repository.CategoryOfRealEstateRepository;
import com.demo.real_estate.repository.UserRepository;
import com.demo.real_estate.request.EmployeeRequest;
import com.demo.real_estate.service.CategoryOfRealEstateService;
import com.demo.real_estate.service.EmployeeService;
import com.demo.real_estate.service.UserService;
import com.demo.real_estate.utils.mapper.UserMapper;

import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;

@RestController // @Controller + @ResponseBody
@RequestMapping("user")
public class UserController {
	
	@Autowired
	private UserService uService;
	
	@Autowired
	private UserRepository uRepo;
	
	@GetMapping()
	public ResponseEntity<List<UserDTO>> getUsers(SecurityContextHolderAwareRequestWrapper request, HttpServletResponse response) {
		
		response.setHeader("X-Total-Count", String.valueOf(uService.getAll().size()));
		response.setHeader("access-control-expose-headers", "X-Total-Count");

		return new ResponseEntity<List<UserDTO>>(uService.getAll(), HttpStatus.OK);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<UserDTO> getSingle(@PathVariable("id") Long id) {
		return  new ResponseEntity<UserDTO>( uService.getSingle(id) , HttpStatus.OK);
	}
	
	@PostMapping()
	public ResponseEntity<UserDTO> save(@RequestBody UserCreationDTO user) {
		return  new ResponseEntity<UserDTO>( uService.save(user) , HttpStatus.OK);
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<UserDTO> updateSingle(@PathVariable("id") Long id,@RequestBody UserUpdateDTO userDTO) {
		userDTO.setId(id);
		return  new ResponseEntity<UserDTO>( uService.updateSingle(userDTO) , HttpStatus.OK);
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<UserDTO> deteleSingle(@PathVariable("id") Long id) {
		return  new ResponseEntity<UserDTO>( uService.delete(id) , HttpStatus.OK);
	}
	
}
