package com.jomm.terroir.business.model;

import java.io.Serializable;
import java.time.ZonedDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import com.jomm.terroir.business.ServiceProduct;

/**
 * This Class is an {@link Entity} representing a product.
 * It uses {@link ServiceProduct} for all its logic operations.
 * It implements {@link Serializable} and has a generated serial version ID.
 * It includes a {@link Site} among other specific attributes.
 * Its properties are persisted in table "tr_product".
 * @author Maic
 */
@Entity
@Table(name="tr_product")
@NamedQuery(name="Product.findAll", query="SELECT p FROM Product p")
public class Product implements Serializable {
	
	/** Generated serial version ID. Do not modify. */
	private static final long serialVersionUID = 1864985598416003134L;

	// Attributes
	@Id
	@GeneratedValue
	@Column(name = "product_id")
	private Long id;
	
	@Column(name = "title")
	@NotNull
	private String title;
	
	@Column(name = "description")
	@NotNull
	private String description;
	
	@Column(name = "quantity")
	private int quantity;
	
	@Column(name = "registration_date", columnDefinition = "timestamp with time zone")
	private ZonedDateTime registrationDate;
	
	@NotNull
	@ManyToOne
	private Site site;
	
	// Getters and Setters
	/**
	 * @return the id
	 */
	public Long getId() {
		return id;
	}

	/**
	 * @param id the id to set
	 */
	public void setId(Long id) {
		this.id = id;
	}

	/**
	 * @return the title
	 */
	public String getTitle() {
		return title;
	}

	/**
	 * @param title the title to set
	 */
	public void setTitle(String title) {
		this.title = title;
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
	public ZonedDateTime getRegistrationDate() {
		return registrationDate;
	}

	/**
	 * @param registrationDate the registrationDate to set
	 */
	public void setRegistrationDate(ZonedDateTime registrationDate) {
		this.registrationDate = registrationDate;
	}

	/**
	 * @return the site
	 */
	public Site getSite() {
		return site;
	}

	/**
	 * @param site the site to set
	 */
	public void setSite(Site site) {
		this.site = site;
	}
}
