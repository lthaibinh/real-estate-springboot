package com.demo.real_estate.service;

import java.util.List;

import com.demo.real_estate.dto.UserCreationDTO;
import com.demo.real_estate.dto.UserDTO;
import com.demo.real_estate.dto.UserUpdateDTO;
import com.demo.real_estate.model.Employee;
import com.demo.real_estate.model.post.AddressOfRealEstate;
import com.demo.real_estate.model.post.CategoryOfRealEstate;
import com.demo.real_estate.model.post.Post;
import com.demo.real_estate.model.user.User;

public interface UserService {
	List<UserDTO> getAll();
	UserDTO getSingle(Long id);
	UserDTO save(UserCreationDTO user);
	UserDTO updateSingle(UserUpdateDTO user);
	UserDTO delete(Long id);
	
}
