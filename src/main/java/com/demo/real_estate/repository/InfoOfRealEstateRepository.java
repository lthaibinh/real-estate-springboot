package com.demo.real_estate.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.demo.real_estate.model.Address;
import com.demo.real_estate.model.Department;
import com.demo.real_estate.model.post.AddressOfRealEstate;
import com.demo.real_estate.model.post.CategoryOfRealEstate;
import com.demo.real_estate.model.post.ImageOfRealEstate;
import com.demo.real_estate.model.post.InfoOfRealEstate;
import com.demo.real_estate.model.post.Post;

@Repository
public interface InfoOfRealEstateRepository extends JpaRepository<InfoOfRealEstate, Long> {
	
}
