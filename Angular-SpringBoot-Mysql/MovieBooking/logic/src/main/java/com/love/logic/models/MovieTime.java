package com.love.logic.models;

import java.time.LocalDate;
import java.time.LocalTime;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.Data;

@Entity
@Table(name="movies")
@Data
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"}) 
public class MovieTime {
	
	@Id
	private String movieId;
	
	private String movieName;
	private String description;
	private String movieCast;
	private int prize;
	private int availableSeats;
	private String trainlarUrl;
	
	private LocalTime addedTime;
	private LocalTime time;
	
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
	private LocalDate date;
	private boolean bookingOpened;
	private String movieStatus;
	
	
    @OneToOne(cascade=CascadeType.ALL,orphanRemoval = true)
	private ScreenDetails screen;

}
