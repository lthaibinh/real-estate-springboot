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
import com.demo.real_estate.model.post.CategoryOfRealEstate;
import com.demo.real_estate.model.post.Post;
import com.demo.real_estate.repository.AddressOfRealEstateRepository;
import com.demo.real_estate.repository.CategoryOfRealEstateRepository;
import com.demo.real_estate.repository.EmployeePaginationRepository;
import com.demo.real_estate.repository.EmployeeRepository;
import com.demo.real_estate.repository.PostRepository;

@Service
public class CategoryOfRealEstateServiceImpl implements CategoryOfRealEstateService {
	
	
	@Autowired
	private CategoryOfRealEstateRepository cRepository;
	
	@Override
	public CategoryOfRealEstate getSingle(Long id) {
		Optional<CategoryOfRealEstate> category = cRepository.findById(id);
		if(category.isPresent()) {
			return category.get();
		}
		throw new RuntimeException("category is not found for the id "+ id);
	}

	@Override
	public List<CategoryOfRealEstate> getAll() {
		// TODO Auto-generated method stub
		return cRepository.findAll();
	}

	@Override
	public CategoryOfRealEstate updateSingle(CategoryOfRealEstate cate) {
		Optional<CategoryOfRealEstate> cate2 = cRepository.findById(cate.getId());
		System.out.println("binhtest sysout" + cate2 + cate.getName() );
		if(cate2.isPresent()) {
			return cRepository.save(cate);
		}
		return null;
	}

	@Override
	public CategoryOfRealEstate save(CategoryOfRealEstate cate) {
		// TODO Auto-generated method stub
		return cRepository.save(cate);
	}
}
