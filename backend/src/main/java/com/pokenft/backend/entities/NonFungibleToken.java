package com.pokenft.backend.entities;

import javax.persistence.*;

@Entity
@Table(name = "nonFungibleToken")
public class NonFungibleToken {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	private String name;
	private String description;
	private String filepath;
	private Double price;
	private boolean owned;

	public NonFungibleToken() {

	}

	public NonFungibleToken(Long id, String name, String description, String filepath, Double price, boolean owned) {
		this.id = id;
		this.name = name;
		this.description = description;
		this.filepath = filepath;
		this.price = price;
		this.owned = owned;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
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

	public boolean isOwned() {
		return owned;
	}

	public void setOwned(boolean owned) {
		this.owned = owned;
	}

}
