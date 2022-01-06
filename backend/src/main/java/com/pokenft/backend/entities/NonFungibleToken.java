package com.pokenft.backend.entities;

import javax.persistence.*;

@Entity
@Table(name = "nonFungibleToken")
public class NonFungibleToken {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;
	private String name;
	private String description;
	private String filepath;
	private Double price;
	private boolean owned;

	public NonFungibleToken() {

	}

	public NonFungibleToken(String name, String description, String filepath, Double price, boolean owned) {
		this.name = name;
		this.description = description;
		this.filepath = filepath;
		this.price = price;
		this.owned = owned;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
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

	@Override
	public String toString() {
		return "NonFungibleToken{" +
				"id=" + id +
				", name='" + name + '\'' +
				", description='" + description + '\'' +
				", filepath='" + filepath + '\'' +
				", price=" + price +
				", owned=" + owned +
				'}';
	}

}
