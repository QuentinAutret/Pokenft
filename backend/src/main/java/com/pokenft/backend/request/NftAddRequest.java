package com.pokenft.backend.request;

import javax.validation.constraints.NotBlank;

public class NftAddRequest {

	@NotBlank
	private String name;

	@NotBlank
	private String creator;

	@NotBlank
	private String filepath;

	@NotBlank
	private Double price;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getCreator() {
		return creator;
	}

	public void setCreator(String creator) {
		this.creator = creator;
	}

	public String getFilepath() {
		return filepath;
	}

	public void setFilepath(String filepath) {
		this.filepath = filepath;
	}

	public Double getPrice() {
		return price;
	}

	public void setPrice(Double price) {
		this.price = price;
	}

}
