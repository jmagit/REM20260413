package com.example.models.entities;

import java.io.Serializable;
import jakarta.persistence.*;
import java.time.LocalDateTime;
import java.util.List;


/**
 * The persistent class for the RENTAL database table.
 * 
 */
@Entity
@NamedQuery(name="Rental.findAll", query="SELECT r FROM Rental r")
public class Rental implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="RENTAL_ID")
	private long rentalId;

	@Column(name="LAST_UPDATE", insertable=false, updatable=false, nullable=false)
	private LocalDateTime lastUpdate;

	@Column(name="RENTAL_DATE")
	private LocalDateTime rentalDate;

	@Column(name="RETURN_DATE")
	private LocalDateTime returnDate;

	//bi-directional many-to-one association to Payment
	@OneToMany(mappedBy="rental")
	private List<Payment> payments;

	//bi-directional many-to-one association to Customer
	@ManyToOne
	@JoinColumn(name="CUSTOMER_ID")
	private Customer customer;

	//bi-directional many-to-one association to Inventory
	@ManyToOne
	@JoinColumn(name="INVENTORY_ID")
	private Inventory inventory;

	//bi-directional many-to-one association to Staff
	@ManyToOne
	@JoinColumn(name="STAFF_ID")
	private Staff staff;

	public Rental() {
	}

	public long getRentalId() {
		return this.rentalId;
	}

	public void setRentalId(long rentalId) {
		this.rentalId = rentalId;
	}

	public LocalDateTime getLastUpdate() {
		return this.lastUpdate;
	}

	public void setLastUpdate(LocalDateTime lastUpdate) {
		this.lastUpdate = lastUpdate;
	}

	public LocalDateTime getRentalDate() {
		return this.rentalDate;
	}

	public void setRentalDate(LocalDateTime rentalDate) {
		this.rentalDate = rentalDate;
	}

	public LocalDateTime getReturnDate() {
		return this.returnDate;
	}

	public void setReturnDate(LocalDateTime returnDate) {
		this.returnDate = returnDate;
	}

	public List<Payment> getPayments() {
		return this.payments;
	}

	public void setPayments(List<Payment> payments) {
		this.payments = payments;
	}

	public Payment addPayment(Payment payment) {
		getPayments().add(payment);
		payment.setRental(this);

		return payment;
	}

	public Payment removePayment(Payment payment) {
		getPayments().remove(payment);
		payment.setRental(null);

		return payment;
	}

	public Customer getCustomer() {
		return this.customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}

	public Inventory getInventory() {
		return this.inventory;
	}

	public void setInventory(Inventory inventory) {
		this.inventory = inventory;
	}

	public Staff getStaff() {
		return this.staff;
	}

	public void setStaff(Staff staff) {
		this.staff = staff;
	}

}