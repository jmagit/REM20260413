package com.example.models.entities;

import java.io.Serializable;
import jakarta.persistence.*;
import java.time.LocalDateTime;
import java.util.List;


/**
 * The persistent class for the INVENTORY database table.
 * 
 */
@Entity
@NamedQuery(name="Inventory.findAll", query="SELECT i FROM Inventory i")
public class Inventory implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="INVENTORY_ID")
	private long inventoryId;

	@Column(name="LAST_UPDATE", insertable=false, updatable=false, nullable=false)
	private LocalDateTime lastUpdate;

	//bi-directional many-to-one association to Film
	@ManyToOne
	@JoinColumn(name="FILM_ID")
	private Film film;

	//bi-directional many-to-one association to Store
	@ManyToOne
	@JoinColumn(name="STORE_ID")
	private Store store;

	//bi-directional many-to-one association to Rental
	@OneToMany(mappedBy="inventory")
	private List<Rental> rentals;

	public Inventory() {
	}

	public long getInventoryId() {
		return this.inventoryId;
	}

	public void setInventoryId(long inventoryId) {
		this.inventoryId = inventoryId;
	}

	public LocalDateTime getLastUpdate() {
		return this.lastUpdate;
	}

	public void setLastUpdate(LocalDateTime lastUpdate) {
		this.lastUpdate = lastUpdate;
	}

	public Film getFilm() {
		return this.film;
	}

	public void setFilm(Film film) {
		this.film = film;
	}

	public Store getStore() {
		return this.store;
	}

	public void setStore(Store store) {
		this.store = store;
	}

	public List<Rental> getRentals() {
		return this.rentals;
	}

	public void setRentals(List<Rental> rentals) {
		this.rentals = rentals;
	}

	public Rental addRental(Rental rental) {
		getRentals().add(rental);
		rental.setInventory(this);

		return rental;
	}

	public Rental removeRental(Rental rental) {
		getRentals().remove(rental);
		rental.setInventory(null);

		return rental;
	}

}