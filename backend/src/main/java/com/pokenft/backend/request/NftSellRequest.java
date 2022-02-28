package com.pokenft.backend.request;

import javax.validation.constraints.NotNull;

public class NftSellRequest {

	@NotNull
	private long id;

	@NotNull
	private Double price;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public Double getPrice() {
		return price;
	}

	public void setPrice(Double price) {
		this.price = price;
	}

}
