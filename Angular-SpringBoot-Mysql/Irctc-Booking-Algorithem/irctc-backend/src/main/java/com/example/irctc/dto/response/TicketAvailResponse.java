package com.example.irctc.dto.response;

import java.time.LocalDate;

import lombok.Data;

@Data
public class TicketAvailResponse {

	public TicketAvailResponse(String classs, String availAble, LocalDate date,Integer prize) {
		super();
		this.classs = classs;
		this.availAble = availAble;
		this.date = date;
		this.prize=prize;
	}
	public TicketAvailResponse() {
		// TODO Auto-generated constructor stub
	}
	private String classs;
	private String availAble;
	private LocalDate date;
	private int prize;
	private boolean loaded=false;
}
