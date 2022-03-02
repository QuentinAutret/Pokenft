package com.pokenft.backend.request;

import javax.validation.constraints.NotNull;
import java.util.Set;

public class NftBuyAllRequest {

	@NotNull
	private Set<Long> nftsId;

	@NotNull
	private long userId;

	public Set<Long> getNftsId() {
		return nftsId;
	}

	public void setId(Set<Long> nftsId) {
		this.nftsId = nftsId;
	}

	public long getUserId() {
		return userId;
	}

	public void setUserId(long userId) {
		this.userId = userId;
	}

}
