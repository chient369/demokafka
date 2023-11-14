package com.vti.service.statics;

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
	private AccountService service;
	
	@KafkaListener(id = "statisticGr", topics = "statistic")
	public void saveAccount(MessageDTO msg) {
		LOGGER.info("Statistic service received event: {}, at :{}", msg.getEvent(), msg.getTimestamp());
		
		ObjectMapper mapper = new ObjectMapper();
		AccountDTO accountDTO = mapper.convertValue(msg.getContent(), AccountDTO.class);			
		boolean rs = service.saveAccount(accountDTO);
	

		if(rs) {
		LOGGER.info("Saved account successfully: {}", accountDTO.getEmail());
		}
		
	}

}
