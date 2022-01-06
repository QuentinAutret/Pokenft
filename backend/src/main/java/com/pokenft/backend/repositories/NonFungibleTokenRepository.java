package com.pokenft.backend.repositories;

import com.pokenft.backend.entities.NonFungibleToken;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface NonFungibleTokenRepository extends CrudRepository<NonFungibleToken, Long> {

	List<NonFungibleToken> findByName(String name);

	NonFungibleToken findById(long id);

}
