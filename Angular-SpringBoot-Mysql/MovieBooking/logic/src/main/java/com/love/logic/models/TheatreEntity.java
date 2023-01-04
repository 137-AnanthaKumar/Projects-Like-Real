package com.love.logic.models;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Entity
@Table(name="theatre")
@Data
public class TheatreEntity {
	
	@Id
	private int id;
	private String theatrename;
	private String location;
	

}
