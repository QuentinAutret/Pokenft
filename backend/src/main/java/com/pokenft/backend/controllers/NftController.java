package com.pokenft.backend.controllers;

import com.pokenft.backend.entities.Nft;
import com.pokenft.backend.entities.User;
import com.pokenft.backend.repositories.NftRepository;
import com.pokenft.backend.repositories.UserRepository;
import com.pokenft.backend.response.MessageResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/nft")
public class NftController {

	private final NftRepository nftRepository;
	private final UserRepository userRepository;

	public NftController(NftRepository nftRepository, UserRepository userRepository) {
		this.nftRepository = nftRepository;
		this.userRepository = userRepository;
	}

	@PutMapping("/add")
	@PreAuthorize("hasRole('ADMIN')")
	public @ResponseBody
	Nft add(@RequestBody String name,
	        @RequestBody String creator,
	        @RequestBody String filePath,
	        @RequestBody Double price) {
		Nft nft = new Nft(name, creator, filePath, price, true, null);
		nftRepository.save(nft);
		return nft;
	}

	@GetMapping(path = "/get")
	public @ResponseBody
	Nft get(@RequestBody long id) {
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
	@PreAuthorize("hasRole('ADMIN')")
	public @ResponseBody
	ResponseEntity<?> delete(@RequestBody long id) {
		Nft nft = nftRepository.findById(id);
		nftRepository.deleteById(id);
		return ResponseEntity.ok(new MessageResponse("Nft supprimé avec succès !"));
	}

	@DeleteMapping(path = "/deleteAll")
	@PreAuthorize("hasRole('ADMIN')")
	public @ResponseBody
	ResponseEntity<?> deleteAll() {
		nftRepository.deleteAll();
		return ResponseEntity.ok(new MessageResponse("Nfts supprimés avec succès !"));
	}

	@PostMapping(path = "/update")
	@PreAuthorize("hasRole('ADMIN')")
	public @ResponseBody
	Nft update(@RequestBody long id,
	           @RequestBody String name,
	           @RequestBody String creator,
	           @RequestBody String filePath,
	           @RequestBody Double price,
	           @RequestBody boolean forSale) {
		Nft nft = nftRepository.findById(id);
		nft.setName(name);
		nft.setCreator(creator);
		nft.setFilepath(filePath);
		nft.setPrice(price);
		nft.setForSale(forSale);
		nftRepository.save(nft);
		return nft;
	}

	@PostMapping(path = "sell")
	@PreAuthorize("hasRole('USER') or hasRole('MODERATOR') or hasRole('ADMIN')")
	public @ResponseBody
	Nft sell(@RequestBody long id,
	         @RequestBody Double price) {
		Nft nft = nftRepository.findById(id);
		nft.setPrice(price);
		nft.setForSale(true);
		nftRepository.save(nft);
		return nft;
	}

	@PostMapping(path = "/buy")
	@PreAuthorize("hasRole('USER') or hasRole('MODERATOR') or hasRole('ADMIN')")
	public @ResponseBody
	Nft buy(@RequestBody long nftId,
	        @RequestBody long userId) {
		Nft nft = nftRepository.findById(nftId);
		User user = userRepository.findById(userId);
		nft.setForSale(false);
		nft.setOwner(user);
		nftRepository.save(nft);
		return nft;
	}

}
