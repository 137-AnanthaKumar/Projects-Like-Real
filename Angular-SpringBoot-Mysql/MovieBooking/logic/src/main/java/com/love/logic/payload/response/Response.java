package com.love.logic.payload.response;



import com.love.logic.models.Bookings;
import com.love.logic.models.MovieTime;
import com.love.logic.payload.request.SeatAvailabily;

import lombok.Data;

@Data
public class Response {
	
	private String errormsg;
	private boolean error;
	private MovieTime obj;
	private SeatAvailabily obj2;
	private Bookings obj3;
	
	
	
	
	public Response(String errormsg, boolean error) {
		super();
		this.errormsg = errormsg;
		this.error = error;
		
	}


	public Response() {
		// TODO Auto-generated constructor stub
	}


	public Response(String error, boolean b, MovieTime obj) {
		this.errormsg = error;
		this.error = b;
		this.obj=obj;
	}


	public Response(String errormsg, boolean error, SeatAvailabily obj2) {
		super();
		this.errormsg = errormsg;
		this.error = error;
		this.obj2 = obj2;
	}


	public Response(String errormsg, boolean error, Bookings obj3) {
		super();
		this.errormsg = errormsg;
		this.error = error;
		this.obj3 = obj3;
	}
	

}
