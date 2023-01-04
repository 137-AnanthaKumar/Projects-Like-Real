package com.collabera.photographer_app.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Responsefiles {
	  private String name;
	  private String url;
	  private String type;
	  private long size;
	  private String category;

}
