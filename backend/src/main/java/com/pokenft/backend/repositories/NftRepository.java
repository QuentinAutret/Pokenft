package com.pokenft.backend.repositories;

import com.pokenft.backend.entities.Nft;
import org.springframework.data.repository.CrudRepository;

public interface NftRepository extends CrudRepository<Nft, Long> {

	Nft findById(long id);

}
