package com.collabera.photographer_app.controller;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import com.collabera.photographer_app.dto.AdminAndUsers;
import com.collabera.photographer_app.dto.UserImages;
import com.collabera.photographer_app.jwtutil.JwtUtil;
import com.collabera.photographer_app.model.AdminUsersModel;
import com.collabera.photographer_app.model.AuthenticateRequest;

import com.collabera.photographer_app.model.Response;
import com.collabera.photographer_app.model.Responsefiles;
import com.collabera.photographer_app.services.AdminAndUsersServices;


@RestController
@CrossOrigin(origins = "*")
public class AdminUsersController {
	
	
	@Autowired
	private AdminAndUsersServices services;
	@Autowired
	private AuthenticationManager authenticationManager;
	@Autowired
	private JwtUtil jwtUtil;
	
	  @Value("${bezkoder.app.path}")
	  private String path;

	
	@PostMapping("/photographer/reg")
	public ResponseEntity<?> insertdata(@RequestBody AdminAndUsers adminAndUsers) {
		
		try {
			Response response=services.regdata(adminAndUsers);
			return new ResponseEntity<Response>(response, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<String>("Something went wrong", HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
	}
	
	
	@PostMapping("/admin/login")
	public ResponseEntity<?> authenticateStudentCredentials(@RequestBody AuthenticateRequest authenticateRequest) throws Exception {
		System.out.println(authenticateRequest.getUserName());
		System.out.println(authenticateRequest.getPassword());
		try {
			authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
					authenticateRequest.getUserName(), authenticateRequest.getPassword()));
		} catch (AuthenticationException e) {
			return ResponseEntity.ok(new Response("invalid usrname or password", null, null,""));
		} catch (NullPointerException e) {
			return ResponseEntity.ok(new Response("invalid usrname or password", null, null,""));
		}

		UserDetails details = services.loadUserByUsername(authenticateRequest.getUserName());
		String userId=services.getUserId(details);
		String jwt = jwtUtil.generateToken(details);
		System.out.println("jwttttttttttttttt" + jwt);

		System.out.println("roleeeeeeeeeeeee" + details.getAuthorities());
		return ResponseEntity
				.ok(new Response("login successfull", jwt, details.getAuthorities(), userId));

		
		
	}
	
	// getting Data using username
		@GetMapping("/{userName}")
		public ResponseEntity<?> getdata(@PathVariable String userName) {

			try {
				Response adminUsersModel = services.getdata(userName);

				return new ResponseEntity<Response>(adminUsersModel, HttpStatus.OK);
			} catch (Exception e) {
				return new ResponseEntity<String>("something went wrong", HttpStatus.INTERNAL_SERVER_ERROR);
			}

		}
		
		
		
	
		@GetMapping("/get/{id}")
				public ResponseEntity<?> getdata(@PathVariable int id) {

					try {
					AdminAndUsers adminAndUsers = services.getdatas(id);

						return new ResponseEntity<AdminAndUsers>(adminAndUsers, HttpStatus.OK);
					} catch (Exception e) {
						return new ResponseEntity<String>("something went wrong", HttpStatus.INTERNAL_SERVER_ERROR);
					}

				}
		
		
		@PutMapping("/edit/{userName}")
		public ResponseEntity<?> updatedata(@PathVariable String userName,
				@RequestBody AdminAndUsers adminAndUsers) {

			try {
				Response response = services.updatedata(userName, adminAndUsers);

				return new ResponseEntity<Response>(response, HttpStatus.OK);
			} catch (Exception e) {
				return new ResponseEntity<String>("something went wrong", HttpStatus.OK);
			}

		}
		
		
		@DeleteMapping("/remove/{id}")
		public ResponseEntity<?> removeAccount(@PathVariable int id) {

			try {
				Response adminResponse = services.removeAccount(id);
				return new ResponseEntity<Response>(adminResponse, HttpStatus.OK);
			} catch (Exception e) {
				return new ResponseEntity<String>("something went wrong", HttpStatus.OK);
			}

		}
		
		@GetMapping("/admin/allphotographers")
		public ResponseEntity<?> getallphotographers() {

			try {
				List<AdminUsersModel> ResponseModels = services.getAllPhotographers();
				return new ResponseEntity<List<AdminUsersModel>>(ResponseModels, HttpStatus.OK);
			} catch (Exception e) {
				return new ResponseEntity<String>("something went wrong", HttpStatus.OK);
			}

		}
		
		
		
		//=============== User Images============//
		
		
		
		@PostMapping("/photo/upload/{catagory}/{userId}")
		public String addcourse(@PathVariable String catagory ,@RequestParam("file") MultipartFile filee, @PathVariable Integer userId) throws Exception {
			System.out.println("jjhh");
//			File file=new File(filee.getImageName());
			String mimetype= null;
			
			 services.addImages(catagory,userId,filee);
			 return "success";
			
//			try {
//				mimetype = Files.probeContentType(file.toPath());
//			} catch (IOException e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			}
//			if((mimetype != null) && ((mimetype.split("/")[1].equals("png") || (mimetype.split("/")[1].equals("jpeg"))))) {
//			   
//				 services.addImages(images,userId,filee);
//			    return "success";
//			}else {
//				return "Failed";
//			}

		
//			try {
//				
//				
//			UserImages images = null;
//				String downloadURL = "";
//				images = services.addImages(file,photograoherId,category);
//
//				downloadURL = ServletUriComponentsBuilder.fromCurrentContextPath().path("/photo/download/")
//						.path(images.getImageId()).toUriString();
//
//				return null;
//			//	return new ImageResponse(images.getFileName(), downloadURL, file.getContentType(), file.getSize(),images.getCategory());
//			} catch (Exception e) {
//
//				throw new Exception("Something Went Wrong");
//			}

		}
		
		@GetMapping(path = "/photo/download/{name}")
		public ResponseEntity<Resource> download(@PathVariable String name) throws IOException {
     System.out.println(" path");
			File file = new File(path + name);
			Path path = Paths.get(file.getAbsolutePath());
			ByteArrayResource resource = new ByteArrayResource(Files.readAllBytes(path));

			return ResponseEntity.ok().headers(services.headers(name)).contentLength(file.length())
					.contentType(MediaType.parseMediaType("application/octet-stream")).body(resource);
		}
		
		
//	
//		@GetMapping("/photo/download/{imageId}")
//		public ResponseEntity<?> downloadCourse(@PathVariable String imageId) throws Exception {
//			
//			 UserImages images=null;
//			 images=services.getImages(imageId);
//			return  ResponseEntity.ok().contentType(MediaType.parseMediaType(images.getFileType()))
//					.header(HttpHeaders.CONTENT_DISPOSITION,
//							"images ; filename\""+images.getImageName()
//							+"\"")
//					.body(new ByteArrayResource(images.getData()));
//			
//		}
//		
		
		@DeleteMapping("/photo/delete/{imageId}")
		public ResponseEntity<?> deleteImage(@PathVariable String imageId) throws Exception {
			
			try {
				services.deleteImage(imageId);
				return new ResponseEntity<String>("successfully deleted", HttpStatus.OK);
			} catch (Exception e) {
				return new ResponseEntity<String>("something went wrong", HttpStatus.OK);
			}
			
		}
		
		@GetMapping("/photo/getall/{photograoherId}")
		public ResponseEntity<?> getImages(@PathVariable int photograoherId) throws Exception {
			
			List<UserImages> list = services.getAllImages(photograoherId);
			return ResponseEntity.ok(list);
			
//			try {
//				List<Responsefiles> images=services.getAllimages(photograoherId).map(dbFile -> {
//				      String fileDownloadUri = ServletUriComponentsBuilder
//				              .fromCurrentContextPath()
//				              .path("/photo/download/")
//				              .path(dbFile.getImageId())
//				              .toUriString();
//
//				          return new Responsefiles(
//				              dbFile.getFileName(),
//				              fileDownloadUri,
//				              dbFile.getFileType(),
//				              dbFile.getData().length,dbFile.getCategory());
//				        }).collect(Collectors.toList());
//
//				        return ResponseEntity.status(HttpStatus.OK).body(images);
//
//			
//			} catch (Exception e) {
//				
//				return new ResponseEntity<String>("something went wrong", HttpStatus.INTERNAL_SERVER_ERROR);
//			}
			
			
		}
		

		
		//search API's//
		
		// getting Data using category
		@GetMapping("/search/get/{category}")
		public ResponseEntity<?> getalldata(@PathVariable String category) {

			try {
			List<AdminUsersModel> adminAndUsers = services.getalldatas(category);

				return new ResponseEntity<List<AdminUsersModel>>(adminAndUsers, HttpStatus.OK);
			} catch (Exception e) {
				return new ResponseEntity<String>("something went wrong", HttpStatus.INTERNAL_SERVER_ERROR);
			}

		}

		
		
}
