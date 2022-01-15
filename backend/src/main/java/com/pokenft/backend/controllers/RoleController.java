package com.pokenft.backend.controllers;

import com.pokenft.backend.entities.Role;
import com.pokenft.backend.model.ERole;
import com.pokenft.backend.repositories.RoleRepository;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/role")
public class RoleController {

	private RoleRepository roleRepository;

	public RoleController(RoleRepository roleRepository) {
		this.roleRepository = roleRepository;
	}

	@PutMapping(path = "/add")
	public @ResponseBody
	Role add(@RequestParam String name) {
		Role role = new Role();
		role.setName(ERole.valueOf(name));
		return role;
	}

}
