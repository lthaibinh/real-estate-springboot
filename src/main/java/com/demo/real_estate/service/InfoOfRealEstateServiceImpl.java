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
import com.demo.real_estate.model.post.InfoOfRealEstate;
import com.demo.real_estate.model.post.Post;
import com.demo.real_estate.repository.AddressOfRealEstateRepository;
import com.demo.real_estate.repository.EmployeePaginationRepository;
import com.demo.real_estate.repository.EmployeeRepository;
import com.demo.real_estate.repository.InfoOfRealEstateRepository;
import com.demo.real_estate.repository.PostRepository;

@Service
public class InfoOfRealEstateServiceImpl implements InfoOfRealEstateService {
	
	
	@Autowired
	private InfoOfRealEstateRepository iRepository;
	
	@Override
	public InfoOfRealEstate getSingle(Long id) {
		Optional<InfoOfRealEstate> info = iRepository.findById(id);
		if(info.isPresent()) {
			return info.get();
		}
		throw new RuntimeException("info is not found for the id "+ id);
	}
	
	@Override
	public InfoOfRealEstate saveAddress(InfoOfRealEstate info) {
		// TODO Auto-generated method stub
		return iRepository.save(info);
	}

	@Override
	public InfoOfRealEstate save(InfoOfRealEstate info) {
		return iRepository.save(info);
	}
}
