package com.pokenft.backend.request;

import javax.validation.constraints.NotBlank;

public class RoleUpdateRequest {

	@NotBlank
	private long id;

	@NotBlank
	private String name;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

}
