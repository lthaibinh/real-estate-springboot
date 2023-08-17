package com.demo.real_estate.upload;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class FileUploadResponse {
	private String fileName;
	private String downloadUri;
	private long size;
}
