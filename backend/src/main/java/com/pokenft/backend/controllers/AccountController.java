package com.pokenft.backend.controllers;

import com.pokenft.backend.entities.Account;
import com.pokenft.backend.repositories.AccountRespository;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class AccountController {

	private AccountRespository accountRespository;

	@PostMapping(path = "/addAccount")
	public @ResponseBody
	String addNewAccount(@RequestParam String firstName, @RequestParam String lastName, @RequestParam String email, @RequestParam String password) {
		Account account = new Account();
		account.setFirstName(firstName);
		account.setLastName(lastName);
		account.setEmail(email);
		account.setPassword(password);
		accountRespository.save(account);
		return "Account saved.";
	}

	@GetMapping(path = "/getAccount")
	public @ResponseBody
	Account getAccount(@RequestParam long id) {
		return accountRespository.findById(id);
	}

	@GetMapping(path = "/accounts")
	public @ResponseBody
	Iterable<Account> getAllUsers() {
		return accountRespository.findAll();
	}

}
