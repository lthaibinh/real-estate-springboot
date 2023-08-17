package com.demo.real_estate.upload;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.method.annotation.MvcUriComponentsBuilder;

import com.demo.real_estate.response.FileInfo;
import com.demo.real_estate.service.FilesStorageService;

@RestController
@RequestMapping("/uploadFile")
public class FileUploadController {
	
	 @Autowired
	  FilesStorageService storageService;
	
	@PostMapping()
	public ResponseEntity<FileUploadResponse> uploadFile(@RequestParam("file") MultipartFile multipartFile) throws IOException{
		String fileName = StringUtils.cleanPath(multipartFile.getOriginalFilename());
		long size = multipartFile.getSize();
		
		String fileCode = FileUploadUtil.saveFile(fileName, multipartFile);
		
		FileUploadResponse response = new FileUploadResponse();
		response.setFileName(fileName);
		response.setSize(size);
		response.setDownloadUri("/downloadFile/" + fileCode);
		
		return new ResponseEntity<FileUploadResponse>(response, HttpStatus.OK);
	}
	
	@PostMapping("/multi")
	public ResponseEntity<List<FileUploadResponse>> multiUploadFile(@RequestParam("file") MultipartFile[] multipartFiles) throws IOException{
		
		List<FileUploadResponse> fileUploadResponses = new ArrayList<>();
		for(MultipartFile multipart: multipartFiles ) {
			String fileName = StringUtils.cleanPath(multipart.getOriginalFilename());
			long size = multipart.getSize();
			String fileCode = FileUploadUtil.saveFile(fileName, multipart);
			FileUploadResponse response = new FileUploadResponse();
			response.setFileName(fileName);
			response.setSize(size);
			response.setDownloadUri("/downloadFile/" + fileCode);
			
			fileUploadResponses.add(response);
		}
		
		return new ResponseEntity<List<FileUploadResponse>>(fileUploadResponses, HttpStatus.OK);
	}
	
	@GetMapping("/files")
	  public ResponseEntity<List<FileInfo>> getListFiles() {
	    List<FileInfo> fileInfos = storageService.loadAll().map(path -> {
	      String filename = path.getFileName().toString();
	      String url = MvcUriComponentsBuilder
	          .fromMethodName(FileUploadController.class, "getFile", path.getFileName().toString()).build().toString();

	      return new FileInfo(filename, url);
	    }).collect(Collectors.toList());

	    return ResponseEntity.status(HttpStatus.OK).body(fileInfos);
	  }
}
