package com.jomm.terroir.business;

import java.io.Serializable;
import java.util.Date;
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
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;

/**
 * This Class is an Entity representing an enterprise.
 * It uses {@link EnterpriseService} for all its logic operations.
 * It implements {@link Serializable} and has a generated serial version ID.
 * It includes an {@link Address} among diverse specific attributes.
 * Its properties are persisted in table "tr_enterprise".
 * @author Maic
 */
@Entity
@Table(name="tr_enterprise")
@NamedQuery(name="EnterpriseEntity.findAll", query="SELECT e FROM EnterpriseEntity e")
public class EnterpriseEntity implements Serializable {
	
	/** Generated serial version ID. Do not modify. */
	private static final long serialVersionUID = 3818285976250730794L;

	// Attributes
	@Id
	@GeneratedValue
	@Column(name = "enterprise_id")
	private long id;
	
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
	
	@Temporal(TemporalType.DATE)
	@Column(name = "creation_date")
	private Date creationDate;
	
	@Column(name = "number_employees")
	private int nbEmployees;
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "signup_date")
	private Date signUpDate;
	
	@OneToMany(targetEntity = SellerEntity.class, mappedBy = "enterprise", cascade = CascadeType.ALL)
	private List<SellerEntity> listSellers;
	
	@OneToMany(targetEntity = SiteEntity.class, mappedBy = "enterprise", cascade = CascadeType.ALL)
	private List<SiteEntity> listSites;
	
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
	public Date getCreationDate() {
		return creationDate;
	}

	/**
	 * @param creationDate the creationDate to set
	 */
	public void setCreationDate(Date creationDate) {
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
	public Date getSignUpDate() {
		return signUpDate;
	}

	/**
	 * @param signUpDate the signUpDate to set
	 */
	public void setSignUpDate(Date signUpDate) {
		this.signUpDate = signUpDate;
	}

	/**
	 * @return the listSellers
	 */
	public List<SellerEntity> getListSellers() {
		return listSellers;
	}

	/**
	 * @param listSellers the listSellers to set
	 */
	public void setListSellers(List<SellerEntity> listSellers) {
		this.listSellers = listSellers;
	}

	/**
	 * @return the listSites
	 */
	public List<SiteEntity> getListSites() {
		return listSites;
	}

	/**
	 * @param listSites the listSites to set
	 */
	public void setListSites(List<SiteEntity> listSites) {
		this.listSites = listSites;
	}
}
