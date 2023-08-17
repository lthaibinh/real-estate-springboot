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
import com.demo.real_estate.model.post.AddressOfRealEstate;
import com.demo.real_estate.model.post.Post;
import com.demo.real_estate.repository.AddressOfRealEstateRepository;
import com.demo.real_estate.repository.EmployeePaginationRepository;
import com.demo.real_estate.repository.EmployeeRepository;
import com.demo.real_estate.repository.PostRepository;

@Service
public class AddressOfRealEstateServiceImpl implements AddressOfRealEstateService {
	
	
	@Autowired
	private AddressOfRealEstateRepository aRepository;
	
	@Override
	public AddressOfRealEstate getSingle(Long id) {
		Optional<AddressOfRealEstate> address = aRepository.findById(id);
		if(address.isPresent()) {
			return address.get();
		}
		throw new RuntimeException("address is not found for the id "+ id);
	}
	@Override
	public AddressOfRealEstate saveAddress(AddressOfRealEstate address) {
		// TODO Auto-generated method stub
		return aRepository.save(address);
	}
}
