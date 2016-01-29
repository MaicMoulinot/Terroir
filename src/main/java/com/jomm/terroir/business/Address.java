package com.jomm.terroir.business;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.validation.constraints.NotNull;

/**
 * This Class is an {@link Embeddable} representing a mailing address.
 * It implements {@link Serializable} and has a generated serial version ID.
 * Its properties are never persisted by themselves but only as part of an Entity.
 * @author Maic
 */
@Embeddable
public class Address implements Serializable {
	
	/** Generated serial version ID. Do not modify. */
	private static final long serialVersionUID = -5588567172905081796L;

	// Attributes
	@Column(name = "address_street")
	private String street;
	
	@Column(name = "address_complement")
	private String complement;
	
	@Column(name = "address_post_code")
	@NotNull
	private String postCode;
	
	@Column(name = "address_town")
	@NotNull
	private String town;
	
	@Column(name = "address_country")
	@NotNull
	private String country;
	
	@Column(name = "address_coordinates")
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
	 * @return the town
	 */
	public String getTown() {
		return town;
	}

	/**
	 * @param town the town to set
	 */
	public void setTown(String town) {
		this.town = town;
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
