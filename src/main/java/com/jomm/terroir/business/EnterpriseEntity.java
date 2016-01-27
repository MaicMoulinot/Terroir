package com.jomm.terroir.business;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;

@Entity(name="tr_enterprise")
@NamedQuery(name="EnterpriseEntity.findAll", query="SELECT e FROM tr_enterprise e")
public class EnterpriseEntity {
	
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

}
