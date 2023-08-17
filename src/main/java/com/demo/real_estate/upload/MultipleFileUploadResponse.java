package com.demo.real_estate.upload;

import java.util.List;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MultipleFileUploadResponse {
	private List<FileUploadResponse> fileUploads;
}
