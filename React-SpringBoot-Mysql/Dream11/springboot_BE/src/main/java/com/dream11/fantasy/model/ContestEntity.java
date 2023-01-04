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
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table
public class ContestEntity {


	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int contestId;
	
	@Column
	private String contestCode;
	@Column
	private String title;
	@Column
	private int contestAmount;
	@Column
	private int totalTeams;
	@Column
	private int maxTeamsPerUser;
	@Column
	private int entreFee;
	
	@Column
	private String contestLock="UNLOCKED";
	@Column
	private int availPlace=0;
	
	@Column
	private int finalWinners;
	
	@Column
	private int winningPercentage;
	
	@Column
	private String contextStatus="Active";
	
	@Column
	private long totalWinning;
	@Column
	private long profits;
	
	
	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	@JoinColumn(name="contestId")
	private List<ContestPlayerEntity> players=new ArrayList<ContestPlayerEntity>();
	
	
	
	public void addPlayer(ContestPlayerEntity contestPlayers) {
		this.players.add(contestPlayers);
		
	}
	
	
}
