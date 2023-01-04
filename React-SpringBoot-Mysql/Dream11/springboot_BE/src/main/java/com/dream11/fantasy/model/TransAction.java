package com.dream11.fantasy.model;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.Data;
@Entity
@Data
public class TransAction {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int transActionId;
	
	private int fromAccontNo;
	
	public TransAction( int fromAccontNo, int toAccount, long transActionAmount, String date,
			String time, String typeOfTracs, String contestCode) {
		super();
		this.fromAccontNo = fromAccontNo;
		this.toAccount = toAccount;
		this.transActionAmount = transActionAmount;
		this.date = date;
		this.time = time;
		this.typeOfTracs = typeOfTracs;
		this.contestCode = contestCode;
	}
	public TransAction() {
		// TODO Auto-generated constructor stub
	}
	private int toAccount;
	private long transActionAmount;
	private String date;
	private String time;
	
	private String typeOfTracs;
	private String contestCode;
	
	

}
