package com.vti.service.notify;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.stereotype.Service;

import com.vti.common.dto.AccountDTO;

@Service
@EnableAsync
public class EmailService {
	private Logger LOGGER = LoggerFactory.getLogger(EmailService.class);
	
	@Value("${com.vti.email}")
	private String emailFrom;
	
	
	@Autowired
	private JavaMailSender javaMailSender;
	
	
	@Async
	public void sendMail(AccountDTO dto) {
		LOGGER.info("Sending email to: {}",dto.getEmail());
		
		try {
			SimpleMailMessage msg = new SimpleMailMessage();
			msg.setFrom(emailFrom);
			msg.setTo(dto.getEmail());
			msg.setSubject("Welcome to Kafka");
			msg.setText("You have successfully registered an account with email:" + dto.getEmail());
			
			javaMailSender.send(msg);
			
		}catch(Exception ex){
			LOGGER.warn("Can't send email to: {}",dto.getEmail());
		}
		
		
		
	}
	

}
