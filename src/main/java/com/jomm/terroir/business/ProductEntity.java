package com.jomm.terroir.business;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;

@Entity
@Table(name="tr_product")
public class ProductEntity implements Serializable {
	
	/** Generated serial version ID. Do not modify. */
	private static final long serialVersionUID = 1864985598416003134L;

	// Attributes
	@Id
	@GeneratedValue
	@Column(name = "product_id")
	private long id;
	
	@Column(name = "title")
	@NotNull
	private String title;
	
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
	private SiteEntity site;
	
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
	 * @return the site
	 */
	public SiteEntity getSite() {
		return site;
	}

	/**
	 * @param site the site to set
	 */
	public void setSite(SiteEntity site) {
		this.site = site;
	}
	
}
