package com.pokenft.backend.repositories;

import com.pokenft.backend.entities.Nft;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface NftRepository extends JpaRepository<Nft, Long> {

	Nft findById(long id);

	Optional<Nft> findByName(String name);

	Boolean existsByName(String name);

}
