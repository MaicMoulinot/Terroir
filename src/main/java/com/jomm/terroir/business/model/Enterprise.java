package com.jomm.terroir.business.model;

import java.io.Serializable;
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
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import com.jomm.terroir.business.ServiceEnterprise;

/**
 * This Class is an {@link Entity} representing an enterprise.
 * It uses {@link ServiceEnterprise} for all its logic operations.
 * It implements {@link Serializable} and has a default serial version ID.
 * It includes an {@link Address} among diverse specific attributes.
 * Its properties are persisted in table {@code tr_enterprise}.
 * @author Maic
 */
@Entity
@Table(name="tr_enterprise")
@NamedQuery(name="Enterprise.findAll", query="SELECT e FROM Enterprise e")
public class Enterprise implements Serializable {
	
	/** Serial version ID. Do not modify unless the type undergoes structural changes affecting serialization. */
	private static final long serialVersionUID = 1L;

	// Attributes
	@Id
	@GeneratedValue
	@Column(name = "enterprise_id")
	private Long id;
	
	@Column(unique = true, name = "trade_name")
	@NotNull
	private String tradeName;
	
	@Column(unique = true, name = "legal_name")
	@NotNull
	private String legalName;
	
	@Column(name = "legal_identification")
	@NotNull
	private String legalIdentification;
	
	@Embedded
	private Address address;
	
	@Column(name = "date_creation", columnDefinition = "date")
	private LocalDate creationDate;
	
	@Column(name = "number_employees")
	private int nbEmployees;
	
	@Column(name = "date_signup", columnDefinition = "timestamp with time zone")
	private ZonedDateTime signUpDate;
	
	@OneToMany(targetEntity = Seller.class, mappedBy = "enterprise", cascade = CascadeType.ALL)
	private List<Seller> listSellers;
	
	@OneToMany(targetEntity = Site.class, mappedBy = "enterprise", cascade = CascadeType.ALL)
	private List<Site> listSites;
	
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
	 * @return the signUpDate
	 */
	public ZonedDateTime getSignUpDate() {
		return signUpDate;
	}

	/**
	 * @param signUpDate the signUpDate to set
	 */
	public void setSignUpDate(ZonedDateTime signUpDate) {
		this.signUpDate = signUpDate;
	}

	/**
	 * @return the listSellers
	 */
	public List<Seller> getListSellers() {
		return listSellers;
	}

	/**
	 * @param listSellers the listSellers to set
	 */
	public void setListSellers(List<Seller> listSellers) {
		this.listSellers = listSellers;
	}

	/**
	 * @return the listSites
	 */
	public List<Site> getListSites() {
		return listSites;
	}

	/**
	 * @param listSites the listSites to set
	 */
	public void setListSites(List<Site> listSites) {
		this.listSites = listSites;
	}
}
