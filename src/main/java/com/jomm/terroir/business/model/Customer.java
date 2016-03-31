package com.jomm.terroir.business.model;

import java.time.LocalDate;
import java.time.ZonedDateTime;

import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.NamedQuery;

/**
 * This Class is an {@link Entity} representing a customer.
 * It extends {@link AbstractUser}, thus it indirectly implements 
 * {@link java.io.Serializable} and has a default serial version ID.
 * It uses {@link com.jomm.terroir.business.ServiceUser} for all its business operations.
 * It includes an {@link Address} and other specific attributes.
 * Its properties and those from its parent {@link AbstractUser} 
 * are persisted in the {@link javax.persistence.Table} named {@code customer}.
 * @author Maic
 */
@Entity
@NamedQuery(name="Customer.findAll", query="SELECT c FROM Customer c")
public class Customer extends AbstractUser {
	
	// Constants //-----------------------------------------------
	/** Serial version ID. Do not modify unless the type undergoes structural changes affecting serialization. */
	private static final long serialVersionUID = 1L;

	// Attributes //----------------------------------------------
	@Column(name = "birth_date", columnDefinition = "date")
	private LocalDate birthDate;
	
	@Column(name = "registration_date", columnDefinition = "timestamp with time zone")
	private ZonedDateTime registrationDate;

	@Embedded
	private Address address;

	// Getters and Setters //-------------------------------------
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
	 * @return the registrationDate
	 */
	public ZonedDateTime getRegistrationDate() {
		return registrationDate;
	}

	/**
	 * @param registrationDate the registrationDate to set
	 */
	public void setRegistrationDate(ZonedDateTime signUpDate) {
		this.registrationDate = signUpDate;
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
