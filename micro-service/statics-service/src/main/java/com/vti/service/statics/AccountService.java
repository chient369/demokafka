package com.vti.service.statics;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.vti.common.dto.AccountDTO;
import com.vti.common.entity.Account;

@Service
public class AccountService {

	@Autowired
	private AccountRepo repo;

	public boolean saveAccount(AccountDTO dto) {
		Account account = new Account();
		account.setEmail(dto.getEmail());
		account.setPassword(dto.getPassword());
		
		Account savedAcc = repo.save(account);
		if (savedAcc != null) {
			return true;
		}
		return false;
	}
}
