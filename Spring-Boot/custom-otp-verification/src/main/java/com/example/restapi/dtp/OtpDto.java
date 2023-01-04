package com.example.restapi.dtp;

public class OtpDto {
	   private int otp;
	   private int defaultOtp;
	   private int noOfAttemt=0;
	   public int getOtp() {
		return otp;
	}
	public void setOtp(int otp) {
		this.otp = otp;
	}
	public int getDefaultOtp() {
		return defaultOtp;
	}
	public void setDefaultOtp(int defaultOtp) {
		this.defaultOtp = defaultOtp;
	}
	public int getNoOfAttemt() {
		return noOfAttemt;
	}
	public void setNoOfAttemt(int noOfAttemt) {
		this.noOfAttemt = noOfAttemt;
	}
	
}
