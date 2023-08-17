package com.demo.real_estate.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.demo.real_estate.model.Address;
import com.demo.real_estate.model.Department;
import com.demo.real_estate.model.address.District;
import com.demo.real_estate.model.address.Province;
import com.demo.real_estate.model.address.Ward;
import com.demo.real_estate.model.user.User;

@Repository
public interface WardRepository extends JpaRepository<Ward, Long> {
	List<Ward> findByProvinceAndDistrict(Province provin, District dis);

}
