package com.vti.account;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/account")
public class AccountController {

	@Autowired
	KafkaTemplate<String, Object> kafkaTemplate;
	
	@PostMapping("/new")
	public AccountDTO create(@RequestBody AccountDTO accountDTO) {
		kafkaTemplate.send("notification", accountDTO);
		kafkaTemplate.send("statistic", accountDTO);
		
		return accountDTO;
	}
}
