package com.vti.service.notify;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.vti.common.dto.AccountDTO;
import com.vti.common.dto.MessageDTO;

@Service
public class MessageService {
	private Logger LOGGER = LoggerFactory.getLogger(MessageService.class);

	@Autowired
	private EmailService emailService;
	
	@KafkaListener(id = "notifycationGr", topics = "notification")
	public void notify(MessageDTO dto) {

			ObjectMapper mapper = new ObjectMapper();
			AccountDTO account = mapper.convertValue(dto.getContent(), AccountDTO.class);			
			emailService.sendMail(account);	
			LOGGER.info("Received event: {}, at :{}, data: {}", dto.getEvent(), dto.getTimestamp(),account.getEmail());
	}
}
