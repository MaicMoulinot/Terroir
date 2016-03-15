package com.jomm.terroir.business.model;

import java.time.LocalDate;
import java.time.ZonedDateTime;

import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

import com.jomm.terroir.business.ServiceUser;

/**
 * This Class is an {@link Entity} representing a customer.
 * It extends {@link AbstractUser}, thus it indirectly implements 
 * {@link java.io.Serializable} and has a default serial version ID.
 * It uses {@link ServiceUser} for all its business operations.
 * It includes an {@link Address} and other specific attributes.
 * Its properties and those from its parent {@link AbstractUser} are persisted in table {@code tr_customer}.
 * @author Maic
 */
@Entity
@Table(name="tr_customer")
@NamedQuery(name="Customer.findAll", query="SELECT c FROM Customer c")
public class Customer extends AbstractUser {
	
	/** Serial version ID. Do not modify unless the type undergoes structural changes affecting serialization. */
	private static final long serialVersionUID = 1L;

	// Attributes
	@Column(name = "date_birth", columnDefinition = "date")
	private LocalDate birthDate;
	
	@Column(name = "date_signup", columnDefinition = "timestamp with time zone")
	private ZonedDateTime signUpDate;

	@Embedded
	private Address address;

	// Getters and Setters
	/**
	 * @return the birthDate
	 */
	public LocalDate getBirthDate() {
		return birthDate;
	}

	/**
	 * @param birthDate the birthDate to set
	 */
	public void setBirthDate(LocalDate birthDate) {
		this.birthDate = birthDate;
	}

	/**
	 * @return the signUpDate
	 */
	public ZonedDateTime getSignUpDate() {
		return signUpDate;
	}

	/**
	 * @param signUpDate the signUpDate to set
	 */
	public void setSignUpDate(ZonedDateTime signUpDate) {
		this.signUpDate = signUpDate;
	}

	/**
	 * @return the address
	 */
	public Address getAddress() {
		return address;
	}

	/**
	 * @param address the address to set
	 */
	public void setAddress(Address address) {
		this.address = address;
	}
}
