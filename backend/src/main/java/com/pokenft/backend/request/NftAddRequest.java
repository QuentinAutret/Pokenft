package com.pokenft.backend.request;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

public class NftAddRequest {

	@NotNull
	@NotBlank
	private String name;

	@NotNull
	@NotBlank
	private String creator;

	@NotNull
	@NotBlank
	private String filepath;

	@NotNull
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
