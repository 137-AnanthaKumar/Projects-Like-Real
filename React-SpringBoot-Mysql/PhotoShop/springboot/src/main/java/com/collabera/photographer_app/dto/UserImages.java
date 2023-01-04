package com.collabera.photographer_app.dto;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;

import org.hibernate.annotations.GenericGenerator;



import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@NoArgsConstructor
public class UserImages {
	
	@Id
	@GeneratedValue(generator = "uuid")
	@GenericGenerator(name="uuid",strategy = "uuid2")
	private String imageId;
	private String description;
	
	private String category;
	private String imageName;
	private long imageSize;
	
	
	private String fileType;
	
	@Lob
	private byte[] data;
	
	
	@ManyToOne
	@JoinColumn(name="userId")
	private AdminAndUsers user;


	public UserImages(String category, String fileName, String fileType, byte[] data) {
		super();
		this.category = category;
		this.imageName = fileName;
		this.fileType = fileType;
		this.data = data;
	}

	
	


}
