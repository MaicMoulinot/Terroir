package com.jomm.terroir.business.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.validation.constraints.NotNull;

/**
 * This Class is an {@link Embeddable} representing a mailing address.
 * It implements {@link Serializable} and has a default serial version ID.
 * Its properties are never persisted by themselves but only as part of an {@link javax.persistence.Entity}.
 * @author Maic
 */
@Embeddable
public class Address implements Serializable {
	
	// Constants //-----------------------------------------------
	/** Serial version ID. Do not modify unless the type undergoes structural changes affecting serialization. */
	private static final long serialVersionUID = 1L;

	// Attributes //----------------------------------------------
	/** The address' street. */
	@Column(name = "addr_street")
	private String street;
	
	/** The address' complement, if any. */
	@Column(name = "addr_complement")
	private String complement;
	
	/** The address' postcode/zipcode. */
	@NotNull
	@Column(name = "addr_post_code", nullable = false)
	private String postCode;
	
	/** The address' city. */
	@NotNull
	@Column(name = "addr_city", nullable = false)
	private String city;
	
	/** The address' country. */
	@NotNull
	@Column(name = "addr_country", nullable = false)
	private String country;
	
	/** The address' GPS coordinates. */
	@Column(name = "addr_coordinates")
	private String coordinates;

	// Getters and Setters //-------------------------------------
	/**
	 * @return the street
	 */
	public String getStreet() {
		return street;
	}

	/**
	 * @param street the street to set
	 */
	public void setStreet(String street) {
		this.street = street;
	}

	/**
	 * @return the complement
	 */
	public String getComplement() {
		return complement;
	}

	/**
	 * @param complement the complement to set
	 */
	public void setComplement(String complement) {
		this.complement = complement;
	}

	/**
	 * @return the postCode
	 */
	public String getPostCode() {
		return postCode;
	}

	/**
	 * @param postCode the postCode to set
	 */
	public void setPostCode(String postCode) {
		this.postCode = postCode;
	}

	/**
	 * @return the city
	 */
	public String getCity() {
		return city;
	}

	/**
	 * @param city the city to set
	 */
	public void setCity(String city) {
		this.city = city;
	}

	/**
	 * @return the country
	 */
	public String getCountry() {
		return country;
	}

	/**
	 * @param country the country to set
	 */
	public void setCountry(String country) {
		this.country = country;
	}

	/**
	 * @return the coordinates
	 */
	public String getCoordinates() {
		return coordinates;
	}

	/**
	 * @param coordinates the coordinates to set
	 */
	public void setCoordinates(String coordinates) {
		this.coordinates = coordinates;
	}
}
