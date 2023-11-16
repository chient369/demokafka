package com.vti.account;

import java.time.Instant;
import java.util.Date;
import java.util.Random;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
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
	Logger LOGGER = LoggerFactory.getLogger(AccountController.class);
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

	@GetMapping("/auto-create")
	public ResponseEntity sendMilions() {	
		produceMsg();
		
		return ResponseEntity.ok().build();

	}

	private void produceMsg() {
		for (int i = 0; i < 10000; i++) {
			MessageDTO dto = new MessageDTO();
			AccountDTO accDTO = new AccountDTO();

			try {
				Thread.sleep(500);
				accDTO.setEmail("randomEmail" + i + "@gmail.com");
				accDTO.setPassword(new Random().nextDouble() + "");
				dto.setTimestamp(new Date());
				dto.setContent(accDTO);
				kafkaTemplate.send("notification", dto);
				kafkaTemplate.send("statistic", dto);
				LOGGER.info("Send msg to broker: {}", i);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}

		}
	}

}
