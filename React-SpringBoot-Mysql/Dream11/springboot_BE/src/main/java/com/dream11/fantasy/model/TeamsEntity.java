package com.dream11.fantasy.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import lombok.Data;

@Entity
@Data
@Table
public class TeamsEntity {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int TeamId;
	@Column
	private String teamName;
	
	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	@JoinColumn(name="teamsId")
	private List<PlayersEntity> players=new ArrayList<PlayersEntity>();
	
	
	

}
