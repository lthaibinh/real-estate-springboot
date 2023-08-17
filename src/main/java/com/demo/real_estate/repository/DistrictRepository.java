package com.demo.real_estate.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.demo.real_estate.model.Address;
import com.demo.real_estate.model.Department;
import com.demo.real_estate.model.Employee;
import com.demo.real_estate.model.address.District;
import com.demo.real_estate.model.address.Province;
import com.demo.real_estate.model.user.User;

@Repository
public interface DistrictRepository extends JpaRepository<District, Long> {
	List<District> findByProvince(Province provin);
}
