package com.demo.real_estate.service;

import java.util.List;

import com.demo.real_estate.model.Employee;
import com.demo.real_estate.model.post.AddressOfRealEstate;
import com.demo.real_estate.model.post.InfoOfRealEstate;
import com.demo.real_estate.model.post.Post;

public interface InfoOfRealEstateService {
	InfoOfRealEstate getSingle(Long id);
	
	InfoOfRealEstate save(InfoOfRealEstate info );
	
	InfoOfRealEstate saveAddress(InfoOfRealEstate address);
}
