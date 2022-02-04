package com.pokenft.backend.request;

import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
public class RoleUpdateRequest {

	@NotBlank
	private long id;

	@NotBlank
	private String name;

}
