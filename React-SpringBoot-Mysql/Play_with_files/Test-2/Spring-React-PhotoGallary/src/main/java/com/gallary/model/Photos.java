package com.gallary.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Photos {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	private String imageName;
	private long imageSize;
	
	@Column(columnDefinition = "varchar(255) default ' '")
	private String className;
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getImageName() {
		return imageName;
	}
	public void setImageName(String imageName) {
		this.imageName = imageName;
	}
	public long getImageSize() {
		return imageSize;
	}
	public void setImageSize(long imageSize) {
		this.imageSize = imageSize;
	}
	public String getClassName() {
		return className;
	}
	public void setClassName(String className) {
		this.className = className;
	}
	public Photos(long id, String imageName, long imageSize, String className) {
		super();
		this.id = id;
		this.imageName = imageName;
		this.imageSize = imageSize;
		this.className = className;
	}
	public Photos(String imageName, long imageSize, String className) {
		super();
		this.imageName = imageName;
		this.imageSize = imageSize;
		this.className = className;
	}
	public Photos() {
		super();
		// TODO Auto-generated constructor stub
	}
	@Override
	public String toString() {
		return "Photos [id=" + id + ", imageName=" + imageName + ", imageSize=" + imageSize + ", className=" + className
				+ "]";
	}
	
	
	
}
