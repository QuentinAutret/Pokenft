package com.pokenft.backend.controllers;

import com.pokenft.backend.entities.Nft;
import com.pokenft.backend.entities.User;
import com.pokenft.backend.repositories.NftRepository;
import com.pokenft.backend.repositories.UserRepository;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
public class UserController {

	private final UserRepository userRepository;
	private final NftRepository nftRepository;

	public UserController(UserRepository userRepository, NftRepository nftRepository) {
		this.userRepository = userRepository;
		this.nftRepository = nftRepository;
	}

	@PutMapping(path = "/addUser")
	public @ResponseBody
	String addUser(@RequestParam String pseudo, @RequestParam String firstName, @RequestParam String lastName, @RequestParam String email, @RequestParam String password) {
		User user = new User();
		user.setPseudo(pseudo);
		user.setFirstName(firstName);
		user.setLastName(lastName);
		user.setEmail(email);
		user.setPassword(password);
		userRepository.save(user);
		return "{\n\t\"message\" : \"User : [" + pseudo + "] created & saved.\"\n}";
	}

	@GetMapping(path = "/getUser")
	public @ResponseBody
	User getUser(@RequestParam long id) {
		return userRepository.findById(id);
	}

	@GetMapping(path = "/getAllUsers")
	public @ResponseBody
	Iterable<User> getAllUsers() {
		return userRepository.findAll();
	}

	@DeleteMapping(path = "/deleteUser")
	public @ResponseBody
	String deleteUser(@RequestParam long id) {
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
		return "{\n\t\"message\" : \"User : [" + user.getPseudo() + "] delete & saved.\"\n}";
	}

	@DeleteMapping(path = "/deleteAllUsers")
	public @ResponseBody
	String deleteAllUsers() {
		for (User user : userRepository.findAll()) {
			this.deleteUser(user.getId());
		}
		return "{\n\t\"message\" : \"All users have been deleted\"\n}";
	}

	@PostMapping(path = "/updateUser")
	public @ResponseBody
	User updateUser(@RequestParam long id, @RequestParam String firstName, @RequestParam String lastName, @RequestParam String email, @RequestParam String password) {
		User user = userRepository.findById(id);
		user.setFirstName(firstName);
		user.setLastName(lastName);
		user.setEmail(email);
		user.setPassword(password);
		userRepository.save(user);
		return user;
	}

}
