package com.jomm.terroir.business;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;

@Entity(name="tr_product")
@NamedQuery(name="ProductEntity.findAll", query="SELECT p FROM tr_product p")
public class ProductEntity {
	
	// Attributes
	@Id
	@GeneratedValue
	@Column(name = "product_id")
	private long id;
	
	@Column(name = "name")
	@NotNull
	private String name;
	
	@Column(name = "description")
	@NotNull
	private String description;
	
	@Column(name = "quantity")
	private int quantity;
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "registration_date")
	private Date registrationDate;
	
	@NotNull
	@ManyToOne
	private SiteEntity establishment;
	
	// Getters and Setters
	/**
	 * @return the id
	 */
	public long getId() {
		return id;
	}

	/**
	 * @param id the id to set
	 */
	public void setId(long id) {
		this.id = id;
	}

	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * @return the description
	 */
	public String getDescription() {
		return description;
	}

	/**
	 * @param description the description to set
	 */
	public void setDescription(String description) {
		this.description = description;
	}

	/**
	 * @return the quantity
	 */
	public int getQuantity() {
		return quantity;
	}

	/**
	 * @param quantity the quantity to set
	 */
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	/**
	 * @return the registrationDate
	 */
	public Date getRegistrationDate() {
		return registrationDate;
	}

	/**
	 * @param registrationDate the registrationDate to set
	 */
	public void setRegistrationDate(Date registrationDate) {
		this.registrationDate = registrationDate;
	}

	/**
	 * @return the establishment
	 */
	public SiteEntity getEstablishment() {
		return establishment;
	}

	/**
	 * @param establishment the establishment to set
	 */
	public void setEstablishment(SiteEntity establishment) {
		this.establishment = establishment;
	}
	
}
