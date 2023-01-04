package com.dream11.fantasy.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
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
public class ContestPlayerEntity {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int playerId;
	@Column
	private String team;
	@Column
	private String playerName;
	@Column
	private String role;
	@Column
	private int runs=0;
	@Column
	private int fours=0;
	@Column
	private int six=0;
	@Column
	private int catches=0;
	@Column
	private int wicket=0;
	@Column
	private int runout=0;
	@Column
	private int fifty=0;
	@Column
	private int hundred=0;
	@Column
	private long points=0;
	
}
