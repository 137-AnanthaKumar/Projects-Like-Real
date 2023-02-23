package com.pdf.pdfspringboot;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Entity {

	
	
	 private long id; 
	 private String vehicleName; 
	 private String modelName; 
	 private String brand; 
	 private String vehicleNo;
}
