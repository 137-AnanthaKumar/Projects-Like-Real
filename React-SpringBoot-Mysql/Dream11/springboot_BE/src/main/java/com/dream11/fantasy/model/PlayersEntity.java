package com.dream11.fantasy.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.Data;
@Entity
@Data
public class PlayersEntity {

	

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int playerId;
	
	private String name;
	private String role;
	private long points=0;
	private int catches=0;
	private int runs=0;
	private int four=0;
	private int sixs=0;
	private int wickets=0;
	private int fifty=0;
	private int century=0;
	
	
}
