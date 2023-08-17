package com.demo.real_estate.request;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.demo.real_estate.model.Base64File;
import com.demo.real_estate.model.post.AddressOfRealEstate;
import com.demo.real_estate.model.post.InfoOfRealEstate;
import com.demo.real_estate.model.post.Post;

import lombok.Data;

@Data
public class UpdatePostRequest {
	private PostRequest post;
	private List<Base64File> newBase64Images ;
}
