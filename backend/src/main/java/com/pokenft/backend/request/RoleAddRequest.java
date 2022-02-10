package com.pokenft.backend.request;

import javax.validation.constraints.NotBlank;

public class RoleAddRequest {

	@NotBlank
	private String name;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
}
