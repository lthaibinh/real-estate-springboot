package com.demo.real_estate.request;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.demo.real_estate.dto.AddressCreationDTO;
import com.demo.real_estate.model.Base64File;
import com.demo.real_estate.model.post.AddressOfRealEstate;
import com.demo.real_estate.model.post.InfoOfRealEstate;

import lombok.Data;

@Data
public class PostRequest {
	private String title;
	private String description;
	private AddressCreationDTO address;
	private Long category_id;
	private InfoOfRealEstate info_id;
	private List<Base64File> images ;
}
