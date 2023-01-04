package com.dream11.fantasy.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;

import lombok.Data;
@Entity
@Data
public class MyTeam {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int teamId;
	
	private String teamName="T1";
	
	private int AccountId;
	
	private long totalPoits=0;
	
	private String teamNumber;
	private String teamStatus;
	
	private String accountName;
	
	private int contestId;
	
	private int myRank=0;
	
	private float finalWin=0;
	
	private String contestCode;
	
	private float wiining=0;
	
	private String statusCredit="NOT_CREDITED";
	
	
	
	
	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	@JoinColumn(name="myteamId")
	private List<MyTeamPlayersEntity> myPlayers=new ArrayList<MyTeamPlayersEntity>();
	
	
	public void addPlayer(MyTeamPlayersEntity myTeamPlayersEntity) {
		this.myPlayers.add(myTeamPlayersEntity);
	}

}
