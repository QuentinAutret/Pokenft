package com.pokenft.backend;

import com.pokenft.backend.entities.Account;
import com.pokenft.backend.repositories.AccountRespository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class PokenftApplication {

	private static final Logger log = LoggerFactory.getLogger(PokenftApplication.class);

	public static void main(String[] args) {
		SpringApplication.run(PokenftApplication.class, args);
	}

	@Bean
	public CommandLineRunner demo(AccountRespository accountRespository) {
		return (args) -> {
			accountRespository.save(new Account("Axel", "Bongouvert", "axel@bongouvert.com", "123456"));
			accountRespository.save(new Account("Quentin", "Autret", "quentin@autret.com", "123456"));

			log.info("Accounts found with findAll():");
			log.info("-------------------------------");
			for (Account account : accountRespository.findAll()) {
				log.info(account.toString());
			}
			log.info("");

			Account account = accountRespository.findById(1L);
			log.info("Customer found with findById(1L):");
			log.info("--------------------------------");
			log.info(account.toString());
			log.info("");

			log.info("Account found with findByLastName('Bongouvert'):");
			log.info("--------------------------------------------");
			accountRespository.findByLastName("Bongouvert").forEach(bauer -> {
				log.info(bauer.toString());
			});
			log.info("");
		};
	}

}
