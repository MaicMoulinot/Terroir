package com.jomm.terroir.business.model;

import java.time.LocalDate;
import java.time.ZonedDateTime;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotNull;

/**
 * This Class is an {@link Entity} representing an enterprise.
 * It extends {@link AbstractEntity}, thus it indirectly implements 
 * {@link java.io.Serializable} and has a default serial version ID.
 * It uses {@link com.jomm.terroir.business.ServiceEnterprise} for all its business operations.
 * It includes an {@link Address} among diverse specific attributes.
 * Its properties are persisted in the {@link javax.persistence.Table} named {@code enterprise}.
 * @author Maic
 */
@Entity
@NamedQuery(name="Enterprise.findAll", query="SELECT e FROM Enterprise e")
public class Enterprise extends AbstractEntity {
	
	/** Serial version ID. Do not modify unless the type undergoes structural changes affecting serialization. */
	private static final long serialVersionUID = 1L;

	// Attributes
	@Id
	@GeneratedValue
	@Column(name = "enterprise_id")
	private Long id;
	
	@NotNull
	@Column(name = "trade_name", unique = true)
	private String tradeName;
	
	@NotNull
	@Column(name = "legal_name", unique = true)
	private String legalName;
	
	@NotNull
	@Column(name = "legal_identification", unique = true)
	private String legalIdentification;
	
	@Embedded
	private Address address;
	
	@Column(name = "number_employees")
	private int nbEmployees;
	
	@Column(name = "creation_date", columnDefinition = "date")
	private LocalDate creationDate;
	
	@Column(name = "registration_date", columnDefinition = "timestamp with time zone")
	private ZonedDateTime registrationDate;
	
	@OneToMany(mappedBy = "enterprise", cascade = CascadeType.ALL, orphanRemoval = true)
	private List<Seller> sellers;
	
	@OneToMany(mappedBy = "enterprise", cascade = CascadeType.ALL, orphanRemoval = true)
	private List<Site> sites;
	
	// Getters and Setters
	@Override
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
	 * @return the tradeName
	 */
	public String getTradeName() {
		return tradeName;
	}

	/**
	 * @param tradeName the tradeName to set
	 */
	public void setTradeName(String tradeName) {
		this.tradeName = tradeName;
	}

	/**
	 * @return the legalName
	 */
	public String getLegalName() {
		return legalName;
	}

	/**
	 * @param legalName the legalName to set
	 */
	public void setLegalName(String legalName) {
		this.legalName = legalName;
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
	 * @return the nbEmployees
	 */
	public int getNbEmployees() {
		return nbEmployees;
	}
	
	/**
	 * @param nbEmployees the nbEmployees to set
	 */
	public void setNbEmployees(int nbEmployees) {
		this.nbEmployees = nbEmployees;
	}

	/**
	 * @return the creationDate
	 */
	public LocalDate getCreationDate() {
		return creationDate;
	}

	/**
	 * @param creationDate the creationDate to set
	 */
	public void setCreationDate(LocalDate creationDate) {
		this.creationDate = creationDate;
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
	 * @return the sellers
	 */
	public List<Seller> getSellers() {
		return sellers;
	}

	/**
	 * @param sellers the sellers to set
	 */
	public void setSellers(List<Seller> sellers) {
		this.sellers = sellers;
	}

	/**
	 * @return the listSites
	 */
	public List<Site> getSites() {
		return sites;
	}

	/**
	 * @param listSites the listSites to set
	 */
	public void setSites(List<Site> listSites) {
		this.sites = listSites;
	}
}
