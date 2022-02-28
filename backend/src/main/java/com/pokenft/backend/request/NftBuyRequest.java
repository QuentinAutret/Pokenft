package com.pokenft.backend.request;

import javax.validation.constraints.NotNull;

public class NftBuyRequest {

	@NotNull
	private long id;

	@NotNull
	private long userId;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public long getUserId() {
		return userId;
	}

	public void setUserId(long userId) {
		this.userId = userId;
	}

}
