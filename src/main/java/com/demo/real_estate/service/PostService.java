package com.demo.real_estate.service;

import java.util.List;

import com.demo.real_estate.model.Employee;
import com.demo.real_estate.model.post.Post;

public interface PostService {
	Post save(Post post);
	void delete (Long id);
	List<Post> getPosts();
	
	Post updatePost(Post post);
}
