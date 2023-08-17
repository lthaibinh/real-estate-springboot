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

import com.demo.real_estate.model.Address;
import com.demo.real_estate.model.Employee;
import com.demo.real_estate.model.address.District;
import com.demo.real_estate.model.address.Province;
import com.demo.real_estate.model.address.Street;
import com.demo.real_estate.model.address.Ward;
import com.demo.real_estate.model.post.ImageOfRealEstate;
import com.demo.real_estate.model.post.InfoOfRealEstate;
import com.demo.real_estate.repository.AddressRepository;
import com.demo.real_estate.repository.DistrictRepository;
import com.demo.real_estate.repository.ProvinceRepository;
import com.demo.real_estate.repository.StreetRepository;
import com.demo.real_estate.repository.WardRepository;
import com.demo.real_estate.repository.ImageOfRealEstateRepository;
import com.demo.real_estate.repository.InfoOfRealEstateRepository;
import com.demo.real_estate.request.EmployeeRequest;
import com.demo.real_estate.service.EmployeeService;

import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;

@RestController // @Controller + @ResponseBody

public class AddressController {
	
	@Autowired
	private ProvinceRepository proRepo;
	
	@Autowired
	private ImageOfRealEstateRepository imageRepo;
	
	@Autowired
	private InfoOfRealEstateRepository infoRepo;
	
	@Autowired
	private DistrictRepository disRepo;
	
	@Autowired
	private WardRepository wardRepo;
	
	@Autowired
	private StreetRepository streetRepo;
	
	

	
	@GetMapping("province")
	public ResponseEntity<List<Province>> getAll(SecurityContextHolderAwareRequestWrapper request, HttpServletResponse response) {
		List<Province> provinces = proRepo.findAll();
		response.setHeader("X-Total-Count", String.valueOf(provinces.size()));
		response.setHeader("access-control-expose-headers", "X-Total-Count");
		return  new ResponseEntity<List<Province>>( provinces , HttpStatus.OK);
		
	}
	
	@GetMapping("district/{provinceId}")
	public ResponseEntity<List<District>> getAllDis(@PathVariable Long provinceId,SecurityContextHolderAwareRequestWrapper request, HttpServletResponse response) {
		List<District> provinces = disRepo.findByProvince(proRepo.findById(provinceId).orElse(null));
		response.setHeader("X-Total-Count", String.valueOf(provinces.size()));
		response.setHeader("access-control-expose-headers", "X-Total-Count");
		return  new ResponseEntity<List<District>>( provinces , HttpStatus.OK);
	}
	
	@GetMapping("ward/{provinceId}/{districtId}")
	public ResponseEntity<List<Ward>> getAllDis(@PathVariable Long provinceId, @PathVariable Long districtId,SecurityContextHolderAwareRequestWrapper request, HttpServletResponse response) {
		List<Ward> provinces = wardRepo.findByProvinceAndDistrict(proRepo.findById(provinceId).orElse(null), disRepo.findById(districtId).orElse(null));
		response.setHeader("X-Total-Count", String.valueOf(provinces.size()));
		response.setHeader("access-control-expose-headers", "X-Total-Count");
		return  new ResponseEntity<List<Ward>>( provinces , HttpStatus.OK);
	}
	
	@GetMapping("street/{provinceId}/{districtId}")
	public ResponseEntity<List<Street>> getAllStreets(@PathVariable Long provinceId, @PathVariable Long districtId, SecurityContextHolderAwareRequestWrapper request, HttpServletResponse response) {
		List<Street> provinces = streetRepo.findByProvinceAndDistrict(proRepo.findById(provinceId).orElse(null), disRepo.findById(districtId).orElse(null));
		response.setHeader("X-Total-Count", String.valueOf(provinces.size()));
		response.setHeader("access-control-expose-headers", "X-Total-Count");
		return  new ResponseEntity<List<Street>>( provinces , HttpStatus.OK);
	}
}
