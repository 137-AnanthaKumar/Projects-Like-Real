package com.dream11.fantasy.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.Data;

@Data
@Entity
public class PrizeListCreate {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int rangeId;
	private String contestCode;
	private int fromRank;
	private int toRank;
	private int noOfWinners;
	private int winningAmount;

}
