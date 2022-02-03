package com.pokenft.backend.entities;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.Hibernate;

import javax.persistence.*;
import java.util.Objects;

@Getter
@Setter
@ToString
@RequiredArgsConstructor
@Entity
@Table(name = "nft")
public class Nft {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id")
	private long id;

	private String name;

	private String creator;

	private String filepath;

	private Double price;

	private boolean forSale;

	@OneToOne
	@JoinColumn(name = "user_id", referencedColumnName = "id")
	private User owner;

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
		Nft nft = (Nft) o;
		return Objects.equals(id, nft.id);
	}

	@Override
	public int hashCode() {
		return getClass().hashCode();
	}

}
