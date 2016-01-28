package com.jomm.terroir.business;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.Past;

@Entity
@Table(name="tr_customer")
public class CustomerEntity extends UserEntity implements Serializable {
	
	/** Generated serial version ID. */
	private static final long serialVersionUID = 5245050836890561932L;

	// Attributes
	@Temporal(TemporalType.DATE)
	@Past
	@Column(name = "date_birth")
	private Date birthDate;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "date_signup")
	private Date signUpDate;

	@Embedded
	private Address address;

	// Getters and Setters
	/**
	 * @return the birthDate
	 */
	public Date getBirthDate() {
		return birthDate;
	}

	/**
	 * @param birthDate the birthDate to set
	 */
	public void setBirthDate(Date birthDate) {
		this.birthDate = birthDate;
	}

	/**
	 * @return the signUpDate
	 */
	public Date getSignUpDate() {
		return signUpDate;
	}

	/**
	 * @param signUpDate the signUpDate to set
	 */
	public void setSignUpDate(Date signUpDate) {
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
