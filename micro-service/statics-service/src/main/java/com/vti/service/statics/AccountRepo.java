package com.vti.service.statics;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.vti.common.entity.Account;

@Repository
public interface AccountRepo extends JpaRepository<Account, Integer> {

}
