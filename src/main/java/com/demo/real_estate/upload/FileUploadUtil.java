package com.demo.real_estate.upload;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.Base64;

import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.util.Base64Utils;
import org.springframework.web.multipart.MultipartFile;

public class FileUploadUtil {
	public static String saveFile(String fileName, MultipartFile multipartFile) throws IOException {
		Path uploadDirectory = Paths.get("Files-Upload");
		String fileCode = RandomStringUtils.randomAlphanumeric(8);
		
		try(InputStream inputStream = multipartFile.getInputStream()){
			Path filePath = uploadDirectory.resolve(fileCode+"-"+fileName);
			Files.copy(inputStream, filePath, StandardCopyOption.REPLACE_EXISTING);
		}catch(IOException ioe) {
			throw new IOException("Error saving uploaded file: " + fileCode+"-"+fileName, ioe);
		}
		return fileCode;
	}
	public static String saveFileBase64(String fileName, String base64) throws IOException {
		String fileCode = RandomStringUtils.randomAlphanumeric(8);
		String realFileName = fileCode+"-"+fileName;
		byte[] decodedBytes = Base64.getDecoder().decode(base64);
		Path uploadDirectory = Paths.get("Files-Upload");
		
		
		try(InputStream inputStream = new ByteArrayInputStream(decodedBytes)){
			Path filePath = uploadDirectory.resolve(realFileName);
			Files.copy(inputStream, filePath, StandardCopyOption.REPLACE_EXISTING);
		}catch(IOException ioe) {
			throw new IOException("Error saving uploaded file: " + fileCode+"-"+fileName, ioe);
		}
		return realFileName;
	}
}
