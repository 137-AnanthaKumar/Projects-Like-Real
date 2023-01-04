package com.dream11.fantasy.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.Data;

@Entity
@Data
public class MyTeamPlayersEntity {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int MyTeamPlayersId;
	
	private int playerId;
	
	private String role;
	
	private String name;
	
	private boolean captain=false;
	
	private boolean vc=false;
	
	private long points;

}
