package com.jomm.terroir.business.model;

import java.io.Serializable;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import com.jomm.terroir.business.ServiceSite;

/**
 * This Class is an {@link Entity} representing a site.
 * It uses {@link ServiceSite} for all its logic operations.
 * It implements {@link Serializable} and has a default serial version ID.
 * It includes a {@link Enterprise}, and an {@link Address} among other specific attributes.
 * Its properties are persisted in table "tr_site".
 * @author Maic
 */
@Entity
@Table(name="tr_site")
@NamedQuery(name="Site.findAll", query="SELECT s FROM Site s")
public class Site implements Serializable {
	
	/** Serial version ID. Do not modify unless the type undergoes structural changes affecting serialization. */
	private static final long serialVersionUID = 1L;

	// Attributes
	@Id
	@GeneratedValue
	@Column(name = "site_id")
	private Long id;
	
	@Column(name = "site_name")
	@NotNull
	private String siteName;
	
	@Column(name = "legal_identification")
	@NotNull
	private String legalIdentification;
	
	@Embedded
	private Address address;
	
	@NotNull
	@ManyToOne(optional = false)
	private Enterprise enterprise;
	
	@OneToMany(targetEntity = Product.class, mappedBy = "site", cascade = CascadeType.ALL)
	private List<Product> listProducts;

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
	 * @return the siteName
	 */
	public String getSiteName() {
		return siteName;
	}

	/**
	 * @param siteName the siteName to set
	 */
	public void setSiteName(String siteName) {
		this.siteName = siteName;
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
	public Enterprise getEnterprise() {
		return enterprise;
	}

	/**
	 * @param enterprise the enterprise to set
	 */
	public void setEnterprise(Enterprise enterprise) {
		this.enterprise = enterprise;
	}

	/**
	 * @return the listProducts
	 */
	public List<Product> getListProducts() {
		return listProducts;
	}

	/**
	 * @param listProducts the listProducts to set
	 */
	public void setListProducts(List<Product> listProducts) {
		this.listProducts = listProducts;
	}
	
}
