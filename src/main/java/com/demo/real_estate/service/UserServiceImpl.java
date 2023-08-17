package com.demo.real_estate.service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import com.demo.real_estate.dto.UserCreationDTO;
import com.demo.real_estate.dto.UserDTO;
import com.demo.real_estate.dto.UserUpdateDTO;
import com.demo.real_estate.model.Department;
import com.demo.real_estate.model.Employee;
import com.demo.real_estate.model.post.AddressOfRealEstate;
import com.demo.real_estate.model.post.CategoryOfRealEstate;
import com.demo.real_estate.model.post.Post;
import com.demo.real_estate.model.user.Role;
import com.demo.real_estate.model.user.User;
import com.demo.real_estate.repository.AddressOfRealEstateRepository;
import com.demo.real_estate.repository.CategoryOfRealEstateRepository;
import com.demo.real_estate.repository.EmployeePaginationRepository;
import com.demo.real_estate.repository.EmployeeRepository;
import com.demo.real_estate.repository.PostRepository;
import com.demo.real_estate.repository.RoleRepository;
import com.demo.real_estate.repository.UserRepository;
import com.demo.real_estate.utils.mapper.UserMapper;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserRepository uRepository;

	@Autowired
	private RoleRepository rRepository;

	@Override
	public UserDTO getSingle(Long id) {
		Optional<User> user = uRepository.findById(id);
		if (user.isPresent()) {
			return UserMapper.getInstance().toDTO(user.get());
		}
		throw new RuntimeException("category is not found for the id " + id);
	}

	@Override
	public List<UserDTO> getAll() {
		return uRepository.findAll().stream().map(user -> UserMapper.getInstance().toDTO(user))
				.collect(Collectors.toList());
	}

	@Override
	public UserDTO updateSingle(UserUpdateDTO userDto) {
		Optional<User> user = uRepository.findById(userDto.getId());
		if (user.isPresent()) {
			User user1 = user.get();

			user1.setId(userDto.getId());
			user1.setFirstname(userDto.getFirstname());
			user1.setLastname(userDto.getLastname());
			user1.setEmail(userDto.getEmail());

			List<Role> roles = rRepository.findAllById(userDto.getRole());
			Set<Role> setRoles = new HashSet<>();
			setRoles.addAll(roles);
			user1.setRoles(setRoles);
			User result = uRepository.save(user1);
			UserDTO res = UserMapper.getInstance().toDTO(result);
			return res;
		}
		return null;
	}

	@Override
	public UserDTO save(UserCreationDTO userDto) {
		User user = UserMapper.getInstance().toEntity(userDto);
		List<Role> roles = rRepository.findAllById(userDto.getRole());
		Set<Role> setRoles = new HashSet<>();
		setRoles.addAll(roles);
		user.setRoles(setRoles);
		System.out.println(setRoles);
		User result = uRepository.save(user);
		UserDTO res = UserMapper.getInstance().toDTO(result);
		return res;
	}
//
//	@Override
//	public User delete(Long id) {
//		Optional<User> user = uRepository.findById(id);
//		if(user.isPresent()) {
//			uRepository.deleteById(id);
//			return user.get();
//		}
//		return null;
//	}

	@Override
	public UserDTO delete(Long id) {
		Optional<User> user = uRepository.findById(id);
		if (user.isPresent()) {
			System.out.println("binhtest " + user.get());
			uRepository.deleteById(id);
			return UserMapper.getInstance().toDTO(user.get());
		}
		return null;
	}
}
