package com.pokenft.backend.controllers;

import com.pokenft.backend.entities.Nft;
import com.pokenft.backend.entities.User;
import com.pokenft.backend.repositories.NftRepository;
import com.pokenft.backend.repositories.UserRepository;
import com.pokenft.backend.request.NftAddRequest;
import com.pokenft.backend.request.NftBuyRequest;
import com.pokenft.backend.request.NftSellRequest;
import com.pokenft.backend.request.NftUpdateRequest;
import com.pokenft.backend.response.MessageResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
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
	public ResponseEntity<?> add(@Valid @RequestBody NftAddRequest nftAddRequest) {
		if (nftRepository.existsByName(nftAddRequest.getName())) {
			return ResponseEntity.badRequest().body(new MessageResponse("Erreur: Nom de NFT déjà pris !"));
		}
		Nft nft = new Nft();
		nft.setName(nftAddRequest.getName());
		nft.setCreator(nftAddRequest.getCreator());
		nft.setFilepath(nftAddRequest.getFilepath());
		nft.setPrice(nftAddRequest.getPrice());
		nft.setForSale(true);
		nft.setOwner(null);
		nftRepository.save(nft);
		return ResponseEntity.ok(new MessageResponse("Nft enregistré avec succès !"));
	}

	@GetMapping("/get")
	public @ResponseBody
	Nft get(@RequestParam long id) {
		return nftRepository.findById(id);
	}

	@GetMapping("/getAll")
	public @ResponseBody
	Iterable<Nft> getAll() {
		return nftRepository.findAll();
	}

	@GetMapping("/getAllOnSale")
	public @ResponseBody
	Iterable<Nft> getAllOnSale() {
		List<Nft> nftList = new ArrayList<>();
		for (Nft nft : nftRepository.findAll()) {
			if (nft.isForSale()) nftList.add(nft);
		}
		return nftList;
	}

	@GetMapping("getAllOfUser")
	public @ResponseBody
	Iterable<Nft> getAllOfUser(@RequestParam long id) {
		List<Nft> nftList = new ArrayList<>();
		for (Nft nft : nftRepository.findAll()) {
			if (nft.getOwner() != null) {
				if (nft.getOwner().getId() == id) nftList.add(nft);
			}
		}
		return nftList;
	}

	@DeleteMapping("/delete")
	@PreAuthorize("hasRole('ADMIN')")
	public @ResponseBody
	ResponseEntity<?> delete(@RequestParam long id) {
		Nft nft = nftRepository.findById(id);
		nftRepository.deleteById(id);
		return ResponseEntity.ok(new MessageResponse("Nft supprimé avec succès !"));
	}

	@DeleteMapping("/deleteAll")
	@PreAuthorize("hasRole('ADMIN')")
	public @ResponseBody
	ResponseEntity<?> deleteAll() {
		nftRepository.deleteAll();
		return ResponseEntity.ok(new MessageResponse("Nfts supprimés avec succès !"));
	}

	@PostMapping("/update")
	@PreAuthorize("hasRole('ADMIN')")
	public ResponseEntity<?> update(@Valid @RequestBody NftUpdateRequest nftUpdateRequest) {
		Nft nft = nftRepository.findById(nftUpdateRequest.getId());
		if (nftRepository.existsByName(nftUpdateRequest.getName())) {
			return ResponseEntity.badRequest().body(new MessageResponse("Erreur: Nom de NFT déjà pris !"));
		}
		nft.setName(nftUpdateRequest.getName());
		nft.setCreator(nftUpdateRequest.getCreator());
		nft.setFilepath(nftUpdateRequest.getFilepath());
		nft.setPrice(nftUpdateRequest.getPrice());
		nft.setForSale(nftUpdateRequest.isForSale());
		nftRepository.save(nft);
		return ResponseEntity.ok(new MessageResponse("Nft modifié avec succès !"));
	}

	@PostMapping("sell")
	@PreAuthorize("hasRole('USER') or hasRole('MODERATOR') or hasRole('ADMIN')")
	public @ResponseBody
	Nft sell(@Valid @RequestBody NftSellRequest nftSellRequest) {
		Nft nft = nftRepository.findById(nftSellRequest.getId());
		nft.setPrice(nftSellRequest.getPrice());
		nft.setForSale(true);
		nftRepository.save(nft);
		return nft;
	}

	@PostMapping("/buy")
	@PreAuthorize("hasRole('USER') or hasRole('MODERATOR') or hasRole('ADMIN')")
	public @ResponseBody
	Nft buy(@Valid @RequestBody NftBuyRequest nftBuyRequest) {
		Nft nft = nftRepository.findById(nftBuyRequest.getId());
		User user = userRepository.findById(nftBuyRequest.getUserId());
		nft.setForSale(false);
		nft.setOwner(user);
		nftRepository.save(nft);
		return nft;
	}

}
