package com.example.irctc.dto.response;

import lombok.Data;

@Data
public class AddTrainResponse {

	
	private int trainNo;
	private String trainName;
	public AddTrainResponse(int trainNo, String trainName, int totalCoach, int totalFirstclassAcCouch,
			int totalFirstclassAcCouchSeats, int totalSecondClassAc, int totalSecondClassAcSeats, int totalThirdClassAc,
			int totalThirdClassAcSeats, int totalSeeperClass, int totalSleeperclassSeat) {
		super();
		this.trainNo = trainNo;
		this.trainName = trainName;
		this.totalCoach = totalCoach;
		this.totalFirstclassAcCouch = totalFirstclassAcCouch;
		this.totalFirstclassAcCouchSeats = totalFirstclassAcCouchSeats;
		this.totalSecondClassAc = totalSecondClassAc;
		this.totalSecondClassAcSeats = totalSecondClassAcSeats;
		this.totalThirdClassAc = totalThirdClassAc;
		this.totalThirdClassAcSeats = totalThirdClassAcSeats;
		this.totalSeeperClass = totalSeeperClass;
		this.totalSleeperclassSeat = totalSleeperclassSeat;
	}
	private int totalCoach;
	private int totalFirstclassAcCouch;
	private int totalFirstclassAcCouchSeats;
	private int totalSecondClassAc;
	private int totalSecondClassAcSeats;
    private int totalThirdClassAc;
	private int totalThirdClassAcSeats;
	private int totalSeeperClass;
	private int totalSleeperclassSeat;
}
