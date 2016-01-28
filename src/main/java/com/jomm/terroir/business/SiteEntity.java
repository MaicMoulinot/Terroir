package com.jomm.terroir.business;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;
import javax.validation.constraints.NotNull;

@Entity(name="tr_site")
@NamedQuery(name="SiteEntity.findAll", query="SELECT s FROM tr_site s")
public class SiteEntity implements Serializable {
	
	/** Generated serial version ID. */
	private static final long serialVersionUID = -8527072429035590099L;

	// Attributes
	@Id
	@GeneratedValue
	@Column(name = "site_id")
	private long id;
	
	@Column(name = "name")
	@NotNull
	private String name;
	
	@Column(name = "legal_identification")
	private String legalIdentification;
	
	@Embedded
	private Address address;
	
	@NotNull
	@ManyToOne
	private EnterpriseEntity enterprise;

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
	 * @return the legalIdentification
	 */
	public String getLegalIdentification() {
		return legalIdentification;
	}

	/**
	 * @param legalIdentification the legalIdentification to set
	 */
	public void setLegalIdentification(String legalIdentification) {
		this.legalIdentification = legalIdentification;
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

	/**
	 * @return the enterprise
	 */
	public EnterpriseEntity getEnterprise() {
		return enterprise;
	}

	/**
	 * @param enterprise the enterprise to set
	 */
	public void setEnterprise(EnterpriseEntity enterprise) {
		this.enterprise = enterprise;
	}
	
}
