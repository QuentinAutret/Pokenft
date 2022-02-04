package com.pokenft.backend.request;

import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
public class NftUpdateRequest {

	@NotBlank
	private long id;

	@NotBlank
	private String name;

	@NotBlank
	private String creator;

	@NotBlank
	private String filepath;

	@NotBlank
	private Double price;

	@NotBlank
	private boolean forSale;

}
