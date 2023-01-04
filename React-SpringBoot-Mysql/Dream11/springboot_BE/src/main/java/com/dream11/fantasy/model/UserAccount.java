package com.dream11.fantasy.model;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

import lombok.Data;

@Entity
@Data
public class UserAccount {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int accountId;
	
	private String accountName;
	
	private String actStatus="Intial";
		
	private long currentAmount=100;
	
	private long winningsAmount=0;
	
	private long totalamountjoining=0;
	
	private int totalJoinedContest=0;
	
	private int wonContest=0;
	
	
	@OneToOne(fetch = FetchType.LAZY,cascade = CascadeType.ALL)
	@JoinColumn(name="userId")
	private UserEntity user;

}
