package com.pokenft.backend.request;

import javax.validation.constraints.NotNull;

public class NftCancelRequest {

	@NotNull
	private long id;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

}
