package com.cdac.service;

import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;

@Service
@Transactional
@AllArgsConstructor
public class EmailServiceImpl implements EmailService{
	private final JavaMailSender mailSender;
	
	@Override
	public void sendMail(String toEmail, String subject, String message) {
		// TODO Auto-generated method stub
		SimpleMailMessage mailMessage = new SimpleMailMessage();
	    mailMessage.setFrom("adityaandhale1048@gmail.com");
	    mailMessage.setTo(toEmail);
	    mailMessage.setSubject(subject);
	    mailMessage.setText(message);

	    mailSender.send(mailMessage);
		
	}

}
