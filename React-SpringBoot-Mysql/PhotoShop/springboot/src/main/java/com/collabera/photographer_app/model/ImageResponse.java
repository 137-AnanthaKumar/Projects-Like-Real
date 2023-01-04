package com.collabera.photographer_app.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ImageResponse {
	
	private String fileName;
	private String downloadURL;
	private String fileType;
	private long fileSize;
	private String category;
	
}
