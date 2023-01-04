package com.example.irctc.dto.response;

import java.util.ArrayList;
import java.util.List;


import lombok.Data;

@Data
public class TicketResponse {
	
	private long pnr;
	private String fromSta;
	private String toSta;
	private PassengerReviewResponse details;
	private List<PassengerResponse> pass=new ArrayList<PassengerResponse>();

}
