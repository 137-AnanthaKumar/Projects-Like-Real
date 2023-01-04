package com.example.restapi.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class EmailSend {
	 @Autowired
		private JavaMailSender mailSend;
	public void sendEmail(String toEmail, String body, String subject) {
		SimpleMailMessage mailmsg=new SimpleMailMessage();
		mailmsg.setFrom("ananthakumartpy@gmail.com");
		mailmsg.setTo(toEmail);
		mailmsg.setText(body);
		mailmsg.setSubject(subject);
		mailSend.send(mailmsg);
		System.out.println("MailSent Successfully");
	}
}
