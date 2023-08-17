package com.demo.real_estate.response;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.demo.real_estate.model.Base64File;
import com.demo.real_estate.model.post.AddressOfRealEstate;
import com.demo.real_estate.model.post.InfoOfRealEstate;

import lombok.Data;

@Data
public class SuccessRes {
	private String status;
	private List<?> data;
}
