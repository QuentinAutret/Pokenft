package com.pokenft.backend.controllers;

import com.pokenft.backend.entities.Role;
import com.pokenft.backend.model.ERole;
import com.pokenft.backend.repositories.RoleRepository;
import com.pokenft.backend.request.RoleAddRequest;
import com.pokenft.backend.request.RoleUpdateRequest;
import com.pokenft.backend.response.MessageResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Objects;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/role")
public class RoleController {

	private final RoleRepository roleRepository;

	public RoleController(RoleRepository roleRepository) {
		this.roleRepository = roleRepository;
	}

	@PutMapping(path = "/add")
	@PreAuthorize("hasRole('ADMIN')")
	public ResponseEntity<?> add(@Valid @RequestBody RoleAddRequest roleAddRequest) {
		if (roleRepository.existsByName(ERole.valueOf(roleAddRequest.getName()))) {
			return ResponseEntity.badRequest().body(new MessageResponse("Erreur: Rôle déjà pris !"));
		}
		Role role = new Role();
		role.setName(ERole.valueOf(roleAddRequest.getName()));
		roleRepository.save(role);
		return ResponseEntity.ok(new MessageResponse("Rôle enregistré avec succès !"));
	}

	@GetMapping("/get")
	public @ResponseBody
	Role get(@RequestParam long id) {
		return roleRepository.findById(id);
	}

	@GetMapping("/getAll")
	public @ResponseBody
	Iterable<Role> getAll() {
		return roleRepository.findAll();
	}

	@DeleteMapping("/delete")
	@PreAuthorize("hasRole('ADMIN')")
	public @ResponseBody
	ResponseEntity<?> delete(@RequestParam long id) {
		// TODO
		return ResponseEntity.ok(new MessageResponse("Rôle supprimé avec succès !"));
	}

	@DeleteMapping("/deleteAll")
	@PreAuthorize("hasRole('ADMIN')")
	public @ResponseBody
	ResponseEntity<?> deleteAll() {
		for (Role role : roleRepository.findAll()) {
			delete(role.getId());
		}
		return ResponseEntity.ok(new MessageResponse("Utilisateurs supprimés avec succès !"));
	}

	@PostMapping("/update")
	@PreAuthorize("hasRole('ADMIN')")
	public ResponseEntity<?> update(@Valid @RequestBody RoleUpdateRequest roleUpdateRequest) {
		Role role = roleRepository.findById(roleUpdateRequest.getId());
		if (!Objects.equals(roleUpdateRequest.getName(), role.getName().name())) {
			if (roleRepository.existsByName(ERole.valueOf(roleUpdateRequest.getName()))) {
				return ResponseEntity.badRequest().body(new MessageResponse("Erreur: Rôle déjà utilisé !"));
			}
		}
		role.setName(ERole.valueOf(roleUpdateRequest.getName()));
		roleRepository.save(role);
		return ResponseEntity.ok(new MessageResponse("Rôle modifié avec succès !"));
	}

}
