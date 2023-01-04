package com.example.restapi.controller;

import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.restapi.dao.UserRepo;
import com.example.restapi.dtp.OtpDto;
import com.example.restapi.entity.NewUser;
import com.example.restapi.service.EmailSend;
@CrossOrigin("*")
@RestController
@RequestMapping("/otpverifications")
public class UserVerificationController {
	
	@Autowired
	private UserRepo repo;
	
	@Autowired
	private EmailSend sendemail; 
	OtpDto otprr=new OtpDto();
	
	@PostMapping("/getotp/{id}")
	 public ResponseEntity<String> sentOtp(@PathVariable Integer id) {
		 
		 
			    NewUser user=repo.getById(id);
			    if(user.getMobileVerifyStatus().equalsIgnoreCase("Unverified")) {
			    	int h = new Random().nextInt(900000) + 100000;
					String messege="Your One Time PassWord is"+" "+h;
					otprr.setDefaultOtp(h);
					  
					String phoneNo=String.valueOf(user.getMobile());
//					smssend.sendSms(phoneNo, messege);
					return ResponseEntity.ok("Successfully Send Otp..!"+h);
			    }
			    else if(user.getMobileVerifyStatus().equalsIgnoreCase("verified")) {
			    	return ResponseEntity.ok("Your Account Mobile Verification is already done");
			    }
			    else {
			    	return ResponseEntity.ok("Something Wrong");
			    }
				
				
				
		 }
	
	@PutMapping("/verifyotpbyphone/{mobNo}")
	 public  ResponseEntity<String>  VerifyOtpByPhoneNo(@PathVariable Long mobNo,@RequestBody OtpDto otp ){
		
			 

			 if( otprr.getDefaultOtp() !=0 && otp.getOtp()==otprr.getDefaultOtp()) {
				 NewUser userOpj=new NewUser();
				 
				 userOpj=repo.findByMobile(mobNo);
				 userOpj.setMobileVerifyStatus("Verified");
				
				repo.save(userOpj);
//				smssend.sendSms(phoneNo, "otpVerifiCation success...");
				  otprr.setNoOfAttemt(0);
				  return ResponseEntity.ok("Otp Verified SusssesFully..!");
				 }
			 else if(otprr.getNoOfAttemt()==0 && otp.getOtp()!=otprr.getDefaultOtp() ) {
				 otprr.setNoOfAttemt(1);
				 System.out.println(otp.getOtp());
//				smssend.sendSms(phoneNo, "You entered Wrong otp You have 2 left");
                 return ResponseEntity.ok("Wrong Otp .Enter Again.2 left!");
				 }
			 else if(otprr.getNoOfAttemt()==1 && otp.getOtp()!=otprr.getDefaultOtp() ) {
				 otprr.setNoOfAttemt(2);
				 System.out.println(otp.getOtp());
//			     smssend.sendSms(phoneNo, "You entered Wrong otp You have 1left");
                 return ResponseEntity.ok("Wrong Otp .Enter Again.1 left!");

	            }
			 else if( otprr.getNoOfAttemt()==2&& otp.getOtp()!=otprr.getDefaultOtp() ) {
				  otprr.setDefaultOtp(0);
//				  smssend.sendSms(phoneNo, "You Entered Wrong Otp Again and again Plese Try Again..");
                  return ResponseEntity.ok("You Entered Wrong Otp Again and again Plese Try Again..!");
	           }
			 else {
				 return ResponseEntity.ok("SomeThing Wrong Try Again.!");
                  }
			
		}
	
	@PostMapping("/getotpformailverify/{id}")
	 public ResponseEntity<String> sentOtp(@PathVariable int id) {
		 
		      NewUser userOpj=repo.getById(id);
		      System.out.println(userOpj.getMailVerifyStatus());
			    String s=userOpj.getMailVerifyStatus();
			    System.out.println(s);
			    
			    String s2="UnVerified";
			    String s3="Verified";
			    
				
				if(s.equalsIgnoreCase(s2)) {
					int h = new Random().nextInt(900000) + 100000;
					String messege="Your One Time PassWord is"+" "+h;
//					sendemail.sendEmail(userOpj.getMail(), messege, "ForVerification");
					otprr.setDefaultOtp(h);
//					smssend.sendSms(phoneNo, messege);
					
					return ResponseEntity.ok("Successfully Sent Otp..!"+h);
				}
				else if(s.equalsIgnoreCase(s3)){
//					sendemail.sendEmail(userOpj.getMail(), "ForVerification");
					return ResponseEntity.ok("Mail Already verified");
				}
				else {
					return ResponseEntity.ok("Something Wrong");
				}
				
				
				
		 }
	
	@PutMapping("/verifyotpbymail/{id}")
	 public  ResponseEntity<String>  VerifyOtpByMail(@PathVariable int id,@RequestBody OtpDto otp ){
		
			 

			 if( otprr.getDefaultOtp() !=0 && otp.getOtp()==otprr.getDefaultOtp()) {
				 NewUser userOpj=new NewUser();
				
				 userOpj=repo.getById(id);
				 if(userOpj.getMailVerifyStatus().equalsIgnoreCase("UnVerified")) {
					 userOpj.setMailVerifyStatus("Verified");
					 repo.save(userOpj);
					 return ResponseEntity.ok("Email Verified SusssesFully..!");
				 }
				 else if(userOpj.getMailVerifyStatus().equalsIgnoreCase("Verified")) {
					 return ResponseEntity.ok("Email Already Verified..!");
				 }
				 else {
					 
					 
					 return ResponseEntity.ok("Something Wrong Please Try again");
				 }
				 
				
				 
				
				 }
			 else if(otprr.getNoOfAttemt()==0 && otp.getOtp()!=otprr.getDefaultOtp() ) {
				 otprr.setNoOfAttemt(1);
				 System.out.println(otp.getOtp());
//				 System.out.println(c);

//					smssend.sendSms(phoneNo, "You entered Wrong otp You have 2 left");

				 return ResponseEntity.ok("Wrong Otp .Enter Again.2 left!");

	           
			 }
			 else if(otprr.getNoOfAttemt()==1 && otp.getOtp()!=otprr.getDefaultOtp() ) {
				 otprr.setNoOfAttemt(2);
				 System.out.println(otp.getOtp());
//				 System.out.println(i);

//				smssend.sendSms(phoneNo, "You entered Wrong otp You have 1left");

				 return ResponseEntity.ok("Wrong Otp .Enter Again.1 left!");

	           
			 }
			 else if( otprr.getNoOfAttemt()==2&& otp.getOtp()!=otprr.getDefaultOtp() ) {
				
				 System.out.println(otp.getOtp());
				 
//				 System.out.println(i);
				 otprr.setDefaultOtp(0);
//					smssend.sendSms(phoneNo, "You Entered Wrong Otp Again and again Plese Try Again..");

				 return ResponseEntity.ok("You Entered Wrong Otp Again and again Plese Try Again..!");
	           
			 }

			
			 else {
				 return ResponseEntity.ok("SomeThing Wrong Try Again.!");

			 }
	}

	
	
}
