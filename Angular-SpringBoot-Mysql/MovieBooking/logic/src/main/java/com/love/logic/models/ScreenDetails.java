package com.love.logic.models;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Entity
@Table(name="screen")
@Data
public class ScreenDetails {
	@Id
	private int screenId;
	private String type; //3d or 3d
	private int totalSeats;
	

}
