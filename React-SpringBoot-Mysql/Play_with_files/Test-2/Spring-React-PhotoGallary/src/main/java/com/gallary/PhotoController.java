package com.gallary;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.List;
import java.util.Random;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.gallary.model.Photos;
import com.gallary.services.PhotoDao;

@RestController
public class PhotoController {
	
	@Autowired
	private PhotoDao dao;
	
	@CrossOrigin
	@PostMapping("/save")
	public String save(@RequestBody Photos p) {
		String fileName = p.getImageName();
		File file = new File(fileName);
		
		System.out.println(p);
		
		
		String mimetype= null;
		try {
			mimetype = Files.probeContentType(file.toPath());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if ((mimetype != null) && ((mimetype.split("/")[1].equals("png") || (mimetype.split("/")[1].equals("jpeg"))))) {
		    System.out.println("it is an image");
		    System.out.println(fileName);
		    System.out.println(p);
		    dao.savePhoto(p);
		    return "success";
		}else {
			return "Failed";
		}
		
	}
	
	@CrossOrigin
	@GetMapping("/all-photos")
	public List<Photos> getAllPhotos() {
		List<Photos> list = dao.getAll();
		return list;
	}

}
