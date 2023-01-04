package com.collabera.photographer_app.services;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;


import java.util.List;
import java.util.stream.Stream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;
import com.collabera.photographer_app.dao.AdminAndUsersDao;
import com.collabera.photographer_app.dao.UserImageDao;
import com.collabera.photographer_app.dto.AdminAndUsers;
import com.collabera.photographer_app.dto.MyAdminAndUsers;
import com.collabera.photographer_app.dto.UserImages;
import com.collabera.photographer_app.model.AdminUsersModel;
import com.collabera.photographer_app.model.Response;

@Service
public class AdminAndUsersServices implements UserDetailsService {

	@Autowired
	private AdminAndUsersDao adminAndUsersDao;
	
	@Autowired
	private UserImageDao userImageDao;
	  @Value("${bezkoder.app.path}")
	  private String path;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		
		
		AdminAndUsers adminAndUsers = adminAndUsersDao.findByUserName(username);
		
		System.out.println(username);
		
		System.out.println(adminAndUsers);

		return new MyAdminAndUsers(adminAndUsers);
	}

	public Response regdata(AdminAndUsers adminUsers) {

		if (adminUsers != null) {
			List<AdminAndUsers> adminUsers1 = adminAndUsersDao.findAll();
			//List<AdminAndUsers> adminUsers1 = adminAndUsersDao.findByRole("ROLE_Admin");
			AdminAndUsers adminUsers2 = adminAndUsersDao.findByUserName(adminUsers.getUserName());
			AdminAndUsers adminUsers3 = adminAndUsersDao.findByPassword(adminUsers.getPassword());

			System.out.println("48888");
//			if(adminUsers1.size()==0) {
//				adminUsers.setRole("ROLE_photographer");
//				adminAndUsersDao.save(adminUsers);
//				return new Response("successfully registered", null, null, null);
//			}
//			
			 if (adminUsers2 != null) {
					return new Response("Change UserName", null, null, "");
				} else if (adminUsers3 != null) {
					return new Response("Change Password", null, null, "");
				} else if (adminUsers2 == null && adminUsers3 == null) {
					adminUsers.setRole("ROLE_photographer");
					adminAndUsersDao.save(adminUsers);
					return new Response("successfully registered", null, null, "");

				}

				
			}
			
		return new Response("Add valid Information", null, null, "");
		}

	
	 public HttpHeaders headers(String name) {
			HttpHeaders header = new HttpHeaders();
			header.add(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=" + name);
			header.add("Cache-Control", "no-cache, no-store, must-revalidate");
			header.add("Pragma", "no-cache");
			header.add("Expires", "0");
			return header;

		}
	 
	public Response getdata(String userName) {
		if (userName != null) {
			AdminAndUsers adminAndUsers = adminAndUsersDao.findByUserName(userName);

			if (adminAndUsers != null) {
				AdminUsersModel response = new AdminUsersModel();
				response.setId(adminAndUsers.getId());
				response.setName(adminAndUsers.getName());
				response.setContact(adminAndUsers.getContact());
				response.setGmail(adminAndUsers.getGmail());
				response.setRole(adminAndUsers.getRole());
				response.setUserName(adminAndUsers.getUserName());
				response.setCategory(adminAndUsers.getCategory());

				return new Response("This is your data", null, null, response);
			} else {
				return new Response("userName is not found please check once", null, null, "");
			}
		}
		return new Response("please enter Your User Name", null, null, "");
	}
	
	
	
	public AdminAndUsers getdatas(int id) {

		if (id > 0) {
			return adminAndUsersDao.findById(id);
		}
		return null;
	}
	
	

	public Response updatedata(String userName, AdminAndUsers adminAndUsers) {
		AdminAndUsers users = adminAndUsersDao.findByUserName(userName);
		if (users == null) {

			return new Response("User Name not found", null, null, "");
		} else {
			users.setName(adminAndUsers.getName());
			users.setContact(adminAndUsers.getContact());
			users.setGmail(adminAndUsers.getGmail());
			users.setRole(adminAndUsers.getRole());
			users.setUserName(adminAndUsers.getUserName());
			users.setPassword(adminAndUsers.getPassword());

			adminAndUsersDao.save(users);
			return new Response("your Data Updated successfully", null, null, "");

		}
	}

	public Response removeAccount(int id) {
		if (id > 0) {
			AdminAndUsers adminAndUsers = adminAndUsersDao.findById(id);
			if (adminAndUsers != null) {
				adminAndUsersDao.delete(adminAndUsers);
				return new Response("Your Account Deleted successfully", null, null, "");
			} else {
				return new Response("Id not found please check you id", null, null, "");
			}
		}
		return new Response("please enter valid Id", null, null, "");
	}


	public List<AdminUsersModel> getAllPhotographers() {
		List<AdminAndUsers> photographers = adminAndUsersDao.findAllPhotographers();
		List<AdminUsersModel> list=new ArrayList<AdminUsersModel>();
		
		for (AdminAndUsers photographer : photographers) {
			AdminUsersModel usersModel=new AdminUsersModel();
			
			usersModel.setId(photographer.getId());
			usersModel.setName(photographer.getName());
			usersModel.setContact(photographer.getContact());
			usersModel.setGmail(photographer.getGmail());
			usersModel.setCategory(photographer.getCategory());
			usersModel.setRole(photographer.getRole());
			usersModel.setUserName(photographer.getUserName());
			list.add(usersModel);
			
		}
		
		return list;
	}

	
	
	
	
	
	
	public UserImages addImages(MultipartFile file, int photograoherId, String category) throws Exception {


			System.out.println("======>2" + file.getOriginalFilename());
			String fileName = StringUtils.cleanPath(file.getOriginalFilename());
			try {

				if (fileName.contains("..")) {
					throw new Exception("File contains invalid path sequence" + fileName);
				} else if (category.isEmpty()) {
					throw new Exception("enter Valid course Name");
				}else {

                 AdminAndUsers user = adminAndUsersDao.findById(photograoherId);
				UserImages  images = new UserImages(category, fileName, file.getContentType(), file.getBytes());
				images.setUser(user);
				System.out.println("======>2" + fileName);
				return userImageDao.save(images);
				}
			} catch (Exception e) {
				throw new Exception("Could not save file" + fileName);
			}
		}
	
	

	public UserImages getImages(String imageId) throws Exception {

	
			UserImages userImages = userImageDao.findByImageId(imageId);
			if (userImages != null) {
				return userImages;
			}
			throw new Exception("file not found exception" + userImages);	
		
		
		
	}

	

	public void deleteImage(String imageId) {
		UserImages userImages = userImageDao.findByImageId(imageId);
		
		deleteImagefromLocal(userImages.getImageName());
		System.out.println("=====>1"+userImages);
		userImageDao.delete(userImages);
		
	}
	
   public void deleteImagefromLocal(String name) {
	   Path imagesPath = Paths.get(path+name);
           try {
			    Files.delete(imagesPath);
			    System.out.println("File "
			            + imagesPath.toAbsolutePath().toString()
			            + " successfully removed");
			} catch (IOException e) {
			    System.err.println("Unable to delete "
			            + imagesPath.toAbsolutePath().toString()
			            + " due to...");
			    e.printStackTrace();
			}
   }
	
	

	public Stream<UserImages> getAllimages(int photograoherId) {
		return userImageDao.findAll(photograoherId).stream();
	}
	
	
	
	
	//================search==============//
	
	
	

	public List<AdminUsersModel> getalldatas(String category) {
	
		List<AdminAndUsers> adminAndUsers=adminAndUsersDao.findByCategory(category);
		List<AdminUsersModel> list=new ArrayList<AdminUsersModel>();
		if (adminAndUsers != null) {
		for (AdminAndUsers adminAndUser : adminAndUsers) {
	
			AdminUsersModel response = new AdminUsersModel();
			response.setId(adminAndUser.getId());
			response.setName(adminAndUser.getName());
			response.setContact(adminAndUser.getContact());
			response.setGmail(adminAndUser.getGmail());
			response.setRole(adminAndUser.getRole());
			response.setUserName(adminAndUser.getUserName());
			response.setCategory(adminAndUser.getCategory());
            list.add(response);
			
		} 
		
		return list;
		}
		return null;
	}
	//private static final String path = "C://Users//thanga.kumar//My Practice//Photoshop//Pet_Project//Front_End//app//public//images/";

	public void addImages(String catagory ,Integer userId,MultipartFile file) throws Exception{
		if (!file.getOriginalFilename().isEmpty()) {
			BufferedOutputStream outputStream = new BufferedOutputStream(
					new FileOutputStream(new File(path, file.getOriginalFilename())));
			outputStream.write(file.getBytes());
			outputStream.flush();
			outputStream.close();
		} else {
			throw new Exception();
		}
		UserImages images=new UserImages();
		images.setDescription("The Discription Option will be soon here");
		images.setImageName(file.getOriginalFilename());
		images.setCategory(catagory);
		images.setImageSize(file.getSize());
		

		AdminAndUsers data=adminAndUsersDao.findById(userId).get();
		images.setUser(data);
		userImageDao.save(images);
	}

	public String getUserId(UserDetails details) {
		 String id=adminAndUsersDao.getUserId(details.getUsername(),details.getPassword());
		return id;
	}

	public List<UserImages> getAllImages(int photograoherId) {
		// TODO Auto-generated method stub
		return userImageDao.getAllImages(photograoherId);
	}
	

	

}
