package com.pokenft.backend.controllers;

import com.pokenft.backend.entities.Nft;
import com.pokenft.backend.entities.Role;
import com.pokenft.backend.entities.User;
import com.pokenft.backend.model.ERole;
import com.pokenft.backend.repositories.NftRepository;
import com.pokenft.backend.repositories.RoleRepository;
import com.pokenft.backend.repositories.UserRepository;
import com.pokenft.backend.request.UserAddRequest;
import com.pokenft.backend.request.UserUpdateRequest;
import com.pokenft.backend.response.MessageResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/user")
public class UserController {

	private final UserRepository userRepository;

	private final RoleRepository roleRepository;

	private final NftRepository nftRepository;

	private final PasswordEncoder passwordEncoder;

	public UserController(UserRepository userRepository, RoleRepository roleRepository, NftRepository nftRepository, PasswordEncoder passwordEncoder) {
		this.userRepository = userRepository;
		this.roleRepository = roleRepository;
		this.nftRepository = nftRepository;
		this.passwordEncoder = passwordEncoder;
	}

	@PutMapping("/add")
	@PreAuthorize("hasRole('ADMIN')")
	public ResponseEntity<?> add(@Valid @RequestBody UserAddRequest userAddRequest) {
		if (userRepository.existsByUsername(userAddRequest.getUsername())) {
			return ResponseEntity.badRequest().body(new MessageResponse("Erreur: Username déjà pris !"));
		}
		if (userRepository.existsByEmail(userAddRequest.getEmail())) {
			return ResponseEntity.badRequest().body(new MessageResponse("Erreur: Email déjà utilisée !"));
		}
		User user = new User();
		user.setUsername(userAddRequest.getUsername());
		user.setFirstName(userAddRequest.getFirstName());
		user.setLastName(userAddRequest.getLastName());
		user.setEmail(userAddRequest.getEmail());
		user.setPassword(passwordEncoder.encode(userAddRequest.getPassword()));
		Set<String> strRoles = userAddRequest.getRole();
		Set<Role> roles = new HashSet<>();
		if (strRoles == null) {
			Role userRole = roleRepository.findByName(ERole.ROLE_USER)
					.orElseThrow(() -> new RuntimeException("Erreur: Role non trouvé."));
			roles.add(userRole);
		} else {
			strRoles.forEach(role -> {
				Role newRole = roleRepository.findByName(ERole.valueOf(role))
						.orElseThrow(() -> new RuntimeException("Erreur: Role non trouvé."));
				roles.add(newRole);
			});
		}
		user.setRoles(roles);
		userRepository.save(user);
		return ResponseEntity.ok(new MessageResponse("Utilisateur enregistré avec succès !"));
	}

	@GetMapping("/get")
	public @ResponseBody
	User get(@RequestParam long id) {
		return userRepository.findById(id);
	}

	@GetMapping("/getAll")
	public @ResponseBody
	Iterable<User> getAll() {
		return userRepository.findAll();
	}

	@DeleteMapping("/delete")
	@PreAuthorize("hasRole('ADMIN')")
	public @ResponseBody
	ResponseEntity<?> delete(@RequestParam long id) {
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
		return ResponseEntity.ok(new MessageResponse("Utilisateur supprimé avec succès !"));
	}

	@DeleteMapping("/deleteAll")
	@PreAuthorize("hasRole('ADMIN')")
	public @ResponseBody
	ResponseEntity<?> deleteAll() {
		for (User user : userRepository.findAll()) {
			delete(user.getId());
		}
		return ResponseEntity.ok(new MessageResponse("Utilisateurs supprimés avec succès !"));
	}

	@PostMapping("/update")
	@PreAuthorize("hasRole('ADMIN')")
	public ResponseEntity<?> update(@Valid @RequestBody UserUpdateRequest userUpdateRequest) {
		User user = userRepository.findByUsername(userUpdateRequest.getUsername())
				.orElseThrow(() -> new RuntimeException("Error: Utilisateur non trouvé."));
		if (!Objects.equals(userUpdateRequest.getEmail(), user.getEmail())) {
			if (userRepository.existsByEmail(userUpdateRequest.getEmail())) {
				return ResponseEntity.badRequest().body(new MessageResponse("Erreur: Email déjà utilisée !"));
			}
		}
		user.setFirstName(userUpdateRequest.getFirstName());
		user.setLastName(userUpdateRequest.getLastName());
		user.setEmail(userUpdateRequest.getEmail());
		user.setPassword(userUpdateRequest.getPassword());
		userRepository.save(user);
		return ResponseEntity.ok(new MessageResponse("Utilisateur modifié avec succès !"));
	}

}
