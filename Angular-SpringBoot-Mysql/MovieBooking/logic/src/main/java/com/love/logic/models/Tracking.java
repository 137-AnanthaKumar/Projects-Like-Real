package com.love.logic.models;

import java.time.LocalDate;
import java.time.LocalTime;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Entity
@Data
@Table(name="tracking")
public class Tracking {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	public Tracking() {
		
	}
	public Tracking(LocalTime time, LocalDate date, String whatdone, String whodid, ERole role) {
		super();
		this.time = time;
		this.date = date;
		this.whatdone = whatdone;
		this.whodid = whodid;
		this.role = role;
	}
	private LocalTime time;
	private LocalDate date;
	private String whatdone;
	private String whodid;
	
	private ERole role;
	
}
