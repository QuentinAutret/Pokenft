package com.pokenft.backend.controllers;

import com.pokenft.backend.entities.Nft;
import com.pokenft.backend.entities.User;
import com.pokenft.backend.repositories.NftRepository;
import com.pokenft.backend.repositories.UserRepository;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("nft")
public class NftController {

	private final NftRepository nftRepository;
	private final UserRepository userRepository;

	public NftController(NftRepository nftRepository, UserRepository userRepository) {
		this.nftRepository = nftRepository;
		this.userRepository = userRepository;
	}

	@PutMapping(path = "/add")
	public @ResponseBody
	Nft add(@RequestParam String name, @RequestParam String creator, @RequestParam String filePath, @RequestParam Double price) {
		Nft nft = new Nft();
		nft.setName(name);
		nft.setCreator(creator);
		nft.setFilepath(filePath);
		nft.setPrice(price);
		nft.setForSale(true);
		nft.setOwner(null);
		nftRepository.save(nft);
		return nft;
	}

	@GetMapping(path = "/get")
	public @ResponseBody
	Nft get(@RequestParam long id) {
		return nftRepository.findById(id);
	}

	@GetMapping(path = "/getAll")
	public @ResponseBody
	Iterable<Nft> getAll() {
		return nftRepository.findAll();
	}

	@GetMapping(path = "/getAllOnSale")
	public @ResponseBody
	Iterable<Nft> getAllOnSale() {
		List<Nft> nftList = new ArrayList<>();
		for (Nft nft : nftRepository.findAll()) {
			if (nft.isForSale()) nftList.add(nft);
		}
		return nftList;
	}

	@GetMapping(path = "getAllOfUser")
	public @ResponseBody
	Iterable<Nft> getAllOfUser(@RequestParam long id) {
		List<Nft> nftList = new ArrayList<>();
		for (Nft nft : nftRepository.findAll()) {
			if (nft.getOwner().getId() == id) nftList.add(nft);
		}
		return nftList;
	}

	@DeleteMapping(path = "/delete")
	public @ResponseBody
	String delete(@RequestParam long id) {
		Nft nft = nftRepository.findById(id);
		nftRepository.deleteById(id);
		return "{\n\t\"message\" : \"L'entrée dans la base de données a été supprimée.\"\n}";
	}

	@DeleteMapping(path = "/deleteAll")
	public @ResponseBody
	String deleteAll() {
		nftRepository.deleteAll();
		return "{\n\t\"message\" : \"Toutes les entrées dans la base de données ont été supprimées.\"\n}";
	}

	@PostMapping(path = "/update")
	public @ResponseBody
	Nft update(@RequestParam long id, @RequestParam String name, @RequestParam String creator, @RequestParam String filePath, @RequestParam Double price, @RequestParam boolean forSale) {
		Nft nft = nftRepository.findById(id);
		nft.setName(name);
		nft.setCreator(creator);
		nft.setFilepath(filePath);
		nft.setPrice(price);
		nft.setForSale(forSale);
		nftRepository.save(nft);
		return nft;
	}

	@PostMapping(path = "/buy")
	public @ResponseBody
	Nft buyNft(@RequestParam long nftId, @RequestParam long userId) {
		User user = userRepository.findById(userId);
		Nft nft = nftRepository.findById(nftId);
		nft.setForSale(false);
		nft.setOwner(user);
		nftRepository.save(nft);
		return nft;
	}

	@PostMapping(path = "onSale")
	public @ResponseBody
	Nft onSaleNft(@RequestParam long id) {
		Nft nft = nftRepository.findById(id);
		nft.setForSale(true);
		nftRepository.save(nft);
		return nft;
	}

	@PostMapping(path = "sell")
	public @ResponseBody
	Nft sellNft(@RequestParam long nftId, @RequestParam long userId) {
		User user = userRepository.findById(userId);
		Nft nft = nftRepository.findById(nftId);
		nft.setOwner(user);
		nftRepository.save(nft);
		return nft;
	}

}
