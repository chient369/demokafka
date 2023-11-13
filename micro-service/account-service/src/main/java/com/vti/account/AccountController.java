package com.vti.account;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.vti.common.dto.AccountDTO;
import com.vti.common.dto.MessageDTO;


@RestController
@RequestMapping("/account")
public class AccountController {
	@Autowired
	KafkaTemplate<String, Object> kafkaTemplate;
	
	@PostMapping("/new")
	public AccountDTO create(@RequestBody AccountDTO accountDTO) {		
		
		MessageDTO dto = new MessageDTO();
		dto.setTimestamp(new Date());
		dto.setEvent("Registration");
		dto.setContent(accountDTO);
		kafkaTemplate.send("notification", dto);
		kafkaTemplate.send("statistic", dto);
		
		return accountDTO;
	}
	
	@GetMapping("/send-milion")
	public void sendMilions() {	
		
		for(int i = 0; i < 10000; i ++) {
			MessageDTO dto = new MessageDTO();
			dto.setTimestamp(new Date());
			dto.setEvent("Test-Millions-msg");
			dto.setContent("Count" + i);
			kafkaTemplate.send("notification", dto);
		}
	}
}
