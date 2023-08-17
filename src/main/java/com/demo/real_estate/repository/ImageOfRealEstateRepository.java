package com.demo.real_estate.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.demo.real_estate.model.Address;
import com.demo.real_estate.model.Department;
import com.demo.real_estate.model.post.AddressOfRealEstate;
import com.demo.real_estate.model.post.CategoryOfRealEstate;
import com.demo.real_estate.model.post.ImageOfRealEstate;
import com.demo.real_estate.model.post.Post;

@Repository
public interface ImageOfRealEstateRepository extends JpaRepository<ImageOfRealEstate, Long> {
	
	
}
