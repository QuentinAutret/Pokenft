package com.pokenft.backend.controllers;

import com.pokenft.backend.entities.Nft;
import com.pokenft.backend.entities.User;
import com.pokenft.backend.repositories.NftRepository;
import com.pokenft.backend.repositories.UserRepository;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("user")
public class UserController {

	private final UserRepository userRepository;
	private final NftRepository nftRepository;

	public UserController(UserRepository userRepository, NftRepository nftRepository) {
		this.userRepository = userRepository;
		this.nftRepository = nftRepository;
	}

	@PutMapping(path = "/add")
	public @ResponseBody
	User add(@RequestParam String pseudo, @RequestParam String firstName, @RequestParam String lastName, @RequestParam String email, @RequestParam String password) {
		User user = new User();
		user.setPseudo(pseudo);
		user.setFirstName(firstName);
		user.setLastName(lastName);
		user.setEmail(email);
		user.setPassword(password);
		userRepository.save(user);
		return user;
	}

	@GetMapping(path = "/get")
	public @ResponseBody
	User get(@RequestParam long id) {
		return userRepository.findById(id);
	}

	@GetMapping(path = "/getAll")
	public @ResponseBody
	Iterable<User> getAll() {
		return userRepository.findAll();
	}

	@DeleteMapping(path = "/delete")
	public @ResponseBody
	String delete(@RequestParam long id) {
		User user = userRepository.findById(id);
		for (Nft nft : nftRepository.findAll()) {
			if (nft.getOwner() != null) {
				if (nft.getOwner().getId() == id) {
					nft.setOwner(null);
					nftRepository.save(nft);
				}
			}
		}
		userRepository.delete(user);
		return "{\n\t\"message\" : \"L'entrée dans la base de données a été supprimée.\"\n}";
	}

	@DeleteMapping(path = "/deleteAll")
	public @ResponseBody
	String deleteAll() {
		userRepository.deleteAll();
		return "{\n\t\"message\" : \"Toutes les entrées dans la base de données ont été supprimées.\"\n}";
	}

	@PostMapping(path = "/update")
	public @ResponseBody
	User update(@RequestParam long id, @RequestParam String firstName, @RequestParam String lastName, @RequestParam String email, @RequestParam String password) {
		User user = userRepository.findById(id);
		user.setFirstName(firstName);
		user.setLastName(lastName);
		user.setEmail(email);
		user.setPassword(password);
		userRepository.save(user);
		return user;
	}

}
