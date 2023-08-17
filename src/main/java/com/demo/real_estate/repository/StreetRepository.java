package com.demo.real_estate.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.demo.real_estate.model.Address;
import com.demo.real_estate.model.Department;
import com.demo.real_estate.model.address.District;
import com.demo.real_estate.model.address.Province;
import com.demo.real_estate.model.address.Street;
import com.demo.real_estate.model.address.Ward;
import com.demo.real_estate.model.user.User;

@Repository
public interface StreetRepository extends JpaRepository<Street, Long> {
	List<Street> findByProvinceAndDistrict(Province provin, District dis);

}
