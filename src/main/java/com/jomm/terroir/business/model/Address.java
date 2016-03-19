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
	
	/** Serial version ID. Do not modify unless the type undergoes structural changes affecting serialization. */
	private static final long serialVersionUID = 1L;

	// Attributes
	@Column(name = "addr_street")
	private String street;
	
	@Column(name = "addr_complement")
	private String complement;
	
	@NotNull
	@Column(name = "addr_post_code")
	private String postCode;
	
	@NotNull
	@Column(name = "addr_city")
	private String city;
	
	@NotNull
	@Column(name = "addr_country")
	private String country;
	
	@Column(name = "addr_coordinates")
	private String coordinates;

	// Getters and Setters
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
