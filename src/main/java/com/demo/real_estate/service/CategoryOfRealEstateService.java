package com.demo.real_estate.service;

import java.util.List;

import com.demo.real_estate.model.Employee;
import com.demo.real_estate.model.post.AddressOfRealEstate;
import com.demo.real_estate.model.post.CategoryOfRealEstate;
import com.demo.real_estate.model.post.Post;

public interface CategoryOfRealEstateService {
	List<CategoryOfRealEstate> getAll();
	CategoryOfRealEstate getSingle(Long id);
	CategoryOfRealEstate save(CategoryOfRealEstate cate);
	CategoryOfRealEstate updateSingle(CategoryOfRealEstate cate);
	
}
