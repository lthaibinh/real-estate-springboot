package com.demo.real_estate.controller;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.servletapi.SecurityContextHolderAwareRequestWrapper;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.demo.real_estate.model.Address;
import com.demo.real_estate.model.Base64File;
import com.demo.real_estate.model.Employee;
import com.demo.real_estate.model.address.District;
import com.demo.real_estate.model.post.AddressOfRealEstate;
import com.demo.real_estate.model.post.ImageOfRealEstate;
import com.demo.real_estate.model.post.InfoOfRealEstate;
import com.demo.real_estate.model.post.Post;
import com.demo.real_estate.model.user.User;
import com.demo.real_estate.repository.AddressOfRealEstateRepository;
import com.demo.real_estate.repository.AddressRepository;
import com.demo.real_estate.repository.DistrictRepository;
import com.demo.real_estate.repository.ProvinceRepository;
import com.demo.real_estate.repository.StreetRepository;
import com.demo.real_estate.repository.WardRepository;
import com.demo.real_estate.repository.ImageOfRealEstateRepository;
import com.demo.real_estate.repository.PostRepository;
import com.demo.real_estate.request.PostRequest;
import com.demo.real_estate.request.UpdatePostRequest;
import com.demo.real_estate.service.AddressOfRealEstateService;
import com.demo.real_estate.service.CategoryOfRealEstateService;
import com.demo.real_estate.service.EmployeeService;
import com.demo.real_estate.service.InfoOfRealEstateService;
import com.demo.real_estate.service.PostService;
import com.demo.real_estate.upload.FileUploadResponse;
import com.demo.real_estate.upload.FileUploadUtil;

import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import lombok.Builder;

@RestController // @Controller + @ResponseBody
@RequestMapping("post")

//@CrossOrigin
public class PostController {
	@Autowired
	private PostRepository pRepo;
	
	@Autowired
	private PostService pService;
	
	@Autowired
	private CategoryOfRealEstateService cService;
	
	
	@Autowired
	private AddressOfRealEstateService aService;
	
	@Autowired
	private InfoOfRealEstateService iService;
	
	@Autowired
	private ImageOfRealEstateRepository imgRepo;
	
	@Autowired
	private ProvinceRepository proRepo;
	
	@Autowired
	private DistrictRepository disRepo;
	
	@Autowired
	private WardRepository wardRepo;
	
	@Autowired
	private StreetRepository streetRepo;
	
	
	@GetMapping("/{id}")
	public ResponseEntity<Post> getEmployee(@PathVariable("id") Long id) {
		Optional<Post> addressOps = pRepo.findById(id);
		Post address = new Post();
		if(addressOps.isPresent()) {
			address = addressOps.get();
			return  new ResponseEntity<Post>( address , HttpStatus.OK);
		}
		throw new RuntimeException("post is not found for the id "+ id);
		
	}
	@GetMapping()
	public ResponseEntity<List<Post>> getEmployees(SecurityContextHolderAwareRequestWrapper request, HttpServletResponse response) throws InterruptedException {
		
		response.setHeader("X-Total-Count", String.valueOf(pService.getPosts().size()));
		response.setHeader("access-control-expose-headers", "X-Total-Count");
		Thread.sleep(5000);
		return new ResponseEntity<List<Post>>(pService.getPosts(), HttpStatus.OK);
	}
	
	@PostMapping()
	//@PreAuthorize("hasAuthority('USER')")
	public ResponseEntity<Post> saveEmployee(@Valid @RequestBody PostRequest pReq) throws IOException {
		// khởi tạo department
		 Collection<? extends GrantedAuthority> authorities = SecurityContextHolder.getContext().getAuthentication().getAuthorities();  //Lấy quyền ánh xạ 1:1 với Role
		 
		Post post = new Post();
		post.setTitle(pReq.getTitle());
		post.setDescription(pReq.getDescription());
		AddressOfRealEstate address = AddressOfRealEstate
										.builder()
										.province(proRepo.findById(pReq.getAddress().getProvinceId()).orElse(null))
										.district(disRepo.findById(pReq.getAddress().getDistrictId()).orElse(null))
										.ward(wardRepo.findById(pReq.getAddress().getWardId()).orElse(null))
										.street(streetRepo.findById(pReq.getAddress().getStreetId()).orElse(null))
										.build();
										
		post.setAddressOfRealEstate(address);
		post.setCategoryOfRealEstate(cService.getSingle(pReq.getCategory_id()));
		InfoOfRealEstate info =  pReq.getInfo_id();
		post.setInfoOfRealEstate(info);
		
//		MultipartFile images = pReq.getImages();
//		String fileName = StringUtils.cleanPath(images.getOriginalFilename());
//		long size = images.getSize();
		List<ImageOfRealEstate> newImages = new ArrayList<ImageOfRealEstate>();
		List<Base64File> images =  pReq.getImages();
		for(Base64File image: images) {
			String srcFileName = FileUploadUtil.saveFileBase64(image.getFileName(), image.getBase64());
			// save images
			String urlFile = "Files-Upload/" + srcFileName ;
			ImageOfRealEstate imageOfRealEstate = new ImageOfRealEstate();
			imageOfRealEstate.setName(image.getFileName());
			imageOfRealEstate.setUrl(urlFile);
			imageOfRealEstate.setInfoOfRealEstate(info);
			Path  pathImage =  Paths.get(urlFile) ;
			imageOfRealEstate.setSize(Files.size(pathImage));
			newImages.add(imageOfRealEstate);
			imgRepo.save(imageOfRealEstate);
		}
		
		return new ResponseEntity<Post>(pService.save(post), HttpStatus.CREATED);
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<Post> updatePost(@PathVariable Long id,@RequestBody UpdatePostRequest updatePostRequest) throws IOException {
		Post post = pRepo.findById(id).orElse(null);
		PostRequest pReq = updatePostRequest.getPost();
		List<Base64File> newBase64Images = updatePostRequest.getNewBase64Images();
		

		
		post.setTitle(pReq.getTitle());
		post.setDescription(pReq.getDescription());
		AddressOfRealEstate address = AddressOfRealEstate
										.builder()
										.province(proRepo.findById(pReq.getAddress().getProvinceId()).orElse(null))
										.district(disRepo.findById(pReq.getAddress().getDistrictId()).orElse(null))
										.ward(wardRepo.findById(pReq.getAddress().getWardId()).orElse(null))
										.street(streetRepo.findById(pReq.getAddress().getStreetId()).orElse(null))
										.build();
		post.setAddressOfRealEstate(address);
		post.setCategoryOfRealEstate(cService.getSingle(pReq.getCategory_id()));
		InfoOfRealEstate info =  pReq.getInfo_id();
		post.setInfoOfRealEstate(info);
		
		List<ImageOfRealEstate> newImages = info.getImage() ;
		
		for(Base64File image: newBase64Images) {
			String srcFileName = FileUploadUtil.saveFileBase64(image.getFileName(), image.getBase64());
			// save images
			String urlFile = "Files-Upload/" + srcFileName ;
			ImageOfRealEstate imageOfRealEstate = new ImageOfRealEstate();
			imageOfRealEstate.setName(image.getFileName());
			imageOfRealEstate.setUrl(urlFile);
			imageOfRealEstate.setInfoOfRealEstate(info);
			Path  pathImage =  Paths.get(urlFile) ;
			imageOfRealEstate.setSize(Files.size(pathImage));
			
			newImages.add(imageOfRealEstate);
		}
		
		return new ResponseEntity<Post>(pService.updatePost(post), HttpStatus.OK);
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<Post>  deleteEmployee(@PathVariable("id") Long id) {
		Optional<Post> post = pRepo.findById(id);
		if (post.isPresent()) {
			pService.delete(id);
			return new ResponseEntity<Post>(post.get(),HttpStatus.OK);
		}
		return null;
	}
	
}
