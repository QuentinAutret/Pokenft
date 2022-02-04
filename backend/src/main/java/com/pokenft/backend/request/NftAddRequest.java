package com.pokenft.backend.request;

import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
public class NftAddRequest {

	@NotBlank
	private String name;

	@NotBlank
	private String creator;

	@NotBlank
	private String filepath;

	@NotBlank
	private Double price;

}
