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
public class NftController {

	private final NftRepository nftRepository;
	private final UserRepository userRepository;

	public NftController(NftRepository nftRepository, UserRepository userRepository) {
		this.nftRepository = nftRepository;
		this.userRepository = userRepository;
	}

	@PutMapping(path = "/addNft")
	public @ResponseBody
	String addUser(@RequestParam String name, @RequestParam String creator, @RequestParam String filePath, @RequestParam Double price) {
		Nft nft = new Nft();
		nft.setName(name);
		nft.setCreator(creator);
		nft.setFilepath(filePath);
		nft.setPrice(price);
		nft.setForSale(true);
		nftRepository.save(nft);
		return "{\n\t\"message\" : \"Nft : [" + name + "] created & saved.\"\n}";
	}

	@GetMapping(path = "/getNft")
	public @ResponseBody
	Nft getNft(@RequestParam long id) {
		return nftRepository.findById(id);
	}

	@GetMapping(path = "/getAllNfts")
	public @ResponseBody
	Iterable<Nft> getAllNfts() {
		return nftRepository.findAll();
	}

	@GetMapping(path = "/getAllOnSaleNfts")
	public @ResponseBody
	Iterable<Nft> getAllOnSaleNfts() {
		List<Nft> nftList = new ArrayList<>();
		for (Nft nft : nftRepository.findAll()) {
			if (nft.isForSale()) nftList.add(nft);
		}
		return nftList;
	}

	@GetMapping(path = "getAllNftsOfUser")
	public @ResponseBody
	Iterable<Nft> getAllNftsOfUser(@RequestParam long id) {
		List<Nft> nftList = new ArrayList<>();
		for (Nft nft : nftRepository.findAll()) {
			if (nft.getOwner().getId() == id) nftList.add(nft);
		}
		return nftList;
	}

	@DeleteMapping(path = "/deleteNft")
	public @ResponseBody
	String deleteNft(@RequestParam long id) {
		Nft nft = nftRepository.findById(id);
		nftRepository.delete(nft);
		return "{\n\t\"message\" : \"Nft : [" + nft.getName() + "] delete & saved.\"\n}";
	}

	@DeleteMapping(path = "/deleteAllNfts")
	public @ResponseBody
	String deleteAllNfts() {
		nftRepository.deleteAll();
		return "{\n\t\"message\" : \"All nfts have been deleted\"\n}";
	}

	@PostMapping(path = "/updateNft")
	public @ResponseBody
	Nft updateNft(@RequestParam long id, @RequestParam String name, @RequestParam String creator, @RequestParam String filePath, @RequestParam Double price, @RequestParam boolean forSale) {
		Nft nft = nftRepository.findById(id);
		nft.setName(name);
		nft.setCreator(creator);
		nft.setFilepath(filePath);
		nft.setPrice(price);
		nft.setForSale(forSale);
		nftRepository.save(nft);
		return nft;
	}

	@PostMapping(path = "/buyNft")
	public @ResponseBody
	Nft buyNft(@RequestParam long nftId, @RequestParam long userId) {
		User user = userRepository.findById(userId);
		Nft nft = nftRepository.findById(nftId);
		nft.setForSale(false);
		nft.setOwner(user);
		nftRepository.save(nft);
		return nft;
	}

	@PostMapping(path = "onSaleNft")
	public @ResponseBody
	Nft onSaleNft(@RequestParam long id) {
		Nft nft = nftRepository.findById(id);
		nft.setForSale(true);
		nftRepository.save(nft);
		return nft;
	}

	@PostMapping(path = "sellNft")
	public @ResponseBody
	Nft sellNft(@RequestParam long nftId, @RequestParam long userId) {
		User user = userRepository.findById(userId);
		Nft nft = nftRepository.findById(nftId);
		nft.setOwner(user);
		nftRepository.save(nft);
		return nft;
	}

}
