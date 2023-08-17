package com.demo.real_estate.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import com.demo.real_estate.model.Department;
import com.demo.real_estate.model.Employee;
import com.demo.real_estate.model.post.Post;
import com.demo.real_estate.repository.EmployeePaginationRepository;
import com.demo.real_estate.repository.EmployeeRepository;
import com.demo.real_estate.repository.PostRepository;

@Service
public class PostServiceImpl implements PostService {
	
	
	@Autowired
	private PostRepository pRepository;
	
	@Override
	public Post save(Post employee) {	
		return pRepository.save(employee);
	}
	
	@Override
	public void delete(Long id) {
		pRepository.deleteById(id);
	}
	@Override
	public List<Post> getPosts() {
		// TODO Auto-generated method stub
		return pRepository.findAll();
	}
	
	@Override
	public Post updatePost(Post post) {
		// TODO Auto-generated method stub
		Optional<Post> newPost = pRepository.findById(post.getId());
		if(newPost.isPresent()) {
			return pRepository.save(post);
		}
		return null;
	}
	
}
