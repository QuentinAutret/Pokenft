package com.pokenft.backend.repositories;

import com.pokenft.backend.entities.Nft;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface NftRepository extends JpaRepository<Nft, Long> {

	Nft findById(long id);

	Boolean existsByName(String name);

}
