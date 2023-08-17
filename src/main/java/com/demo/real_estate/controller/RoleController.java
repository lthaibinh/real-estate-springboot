package com.demo.real_estate.controller;

import java.util.ArrayList;
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

import com.demo.real_estate.dto.RoleDTO;
import com.demo.real_estate.model.Address;
import com.demo.real_estate.model.Employee;
import com.demo.real_estate.model.post.CategoryOfRealEstate;
import com.demo.real_estate.model.post.Post;
import com.demo.real_estate.model.user.Role;
import com.demo.real_estate.repository.AddressRepository;
import com.demo.real_estate.repository.CategoryOfRealEstateRepository;
import com.demo.real_estate.repository.RoleRepository;
import com.demo.real_estate.request.EmployeeRequest;
import com.demo.real_estate.service.CategoryOfRealEstateService;
import com.demo.real_estate.service.EmployeeService;
import com.demo.real_estate.utils.mapper.RoleMapper;
import com.demo.real_estate.utils.mapper.UserMapper;

import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;

@RestController // @Controller + @ResponseBody
@RequestMapping("role")
public class RoleController {
	
	
	@Autowired
	private RoleRepository rRepo;
	
	@GetMapping()
	public ResponseEntity<List<RoleDTO>> getEmployees(SecurityContextHolderAwareRequestWrapper request, HttpServletResponse response) {
		
		response.setHeader("X-Total-Count", String.valueOf(rRepo.findAll().size()));
		response.setHeader("access-control-expose-headers", "X-Total-Count");
		List<Role> roles = rRepo.findAll();
		List<RoleDTO> roleDTOs = new ArrayList<RoleDTO>();
		for(Role role:roles ) {
			roleDTOs.add(RoleMapper.getInstance().toDTO(role));
		}
		
		return new ResponseEntity<List<RoleDTO>>(roleDTOs, HttpStatus.OK);
	}
	
//	@GetMapping("/{id}")
//	public ResponseEntity<CategoryOfRealEstate> getSingle(@PathVariable("id") Long id) {
//		CategoryOfRealEstate cate = cService.getSingle(id);
//		return  new ResponseEntity<CategoryOfRealEstate>( cate , HttpStatus.OK);
//	}
//	
//	@PostMapping()
//	public ResponseEntity<CategoryOfRealEstate> save(@RequestBody CategoryOfRealEstate cate) {
//		return  new ResponseEntity<CategoryOfRealEstate>( cService.save(cate) , HttpStatus.OK);
//	}
//	
//	@PutMapping("/{id}")
//	public ResponseEntity<CategoryOfRealEstate> updateSingle(@PathVariable("id") Long id,@RequestBody CategoryOfRealEstate cate) {
//		cate.setId(id);
//		System.err.println("binhtest le " + cate.getName());
//		return  new ResponseEntity<CategoryOfRealEstate>( cService.updateSingle(cate) , HttpStatus.OK);
//	}
//	
//	@DeleteMapping("/{id}")
//	public ResponseEntity<CategoryOfRealEstate> deteleSingle(@PathVariable("id") Long id) {
//		cRepo.deleteById(id);
//		return  new ResponseEntity<CategoryOfRealEstate>( cService.getSingle(id) , HttpStatus.OK);
//	}
	
}
