package com.pokenft.backend.repositories;

import com.pokenft.backend.entities.Account;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface AccountRespository extends CrudRepository<Account, Long> {

	List<Account> findByLastName(String lastName);

	Account findById(long id);

}
