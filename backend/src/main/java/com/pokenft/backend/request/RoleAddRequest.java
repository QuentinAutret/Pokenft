package com.pokenft.backend.request;

import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
public class RoleAddRequest {

	@NotBlank
	private String name;

}
