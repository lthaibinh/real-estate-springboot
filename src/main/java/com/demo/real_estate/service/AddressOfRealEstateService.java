package com.demo.real_estate.service;

import java.util.List;

import com.demo.real_estate.model.Employee;
import com.demo.real_estate.model.post.AddressOfRealEstate;
import com.demo.real_estate.model.post.Post;

public interface AddressOfRealEstateService {
	AddressOfRealEstate getSingle(Long id);
	
	AddressOfRealEstate saveAddress(AddressOfRealEstate address);
	

}
