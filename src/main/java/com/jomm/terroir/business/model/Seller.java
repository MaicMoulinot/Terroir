package com.jomm.terroir.business.model;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;
import javax.validation.constraints.NotNull;

/**
 * This Class is an {@link Entity} representing a seller.
 * It extends {@link AbstractUser}, thus it indirectly implements 
 * {@link java.io.Serializable} and has a default serial version ID.
 * It uses {@link com.jomm.terroir.business.ServiceUser} for all its business operations.
 * It includes an {@link Enterprise}.
 * Its properties and those from its parent {@link AbstractUser} 
 * are persisted in the {@link javax.persistence.Table} named {@code seller}.
 * @author Maic
 */
@Entity
@NamedQuery(name="Seller.findAll", query="SELECT s FROM Seller s")
public class Seller extends AbstractUser {
	
	// Constants //-----------------------------------------------
	/** Serial version ID. Do not modify unless the type undergoes structural changes affecting serialization. */
	private static final long serialVersionUID = 1L;

	// Attributes //----------------------------------------------
	/** 
	 * The seller's enterprise. Seller is the owning side of the relationship, 
	 * since it contains the foreign key.
	 */
	@NotNull
	@ManyToOne(optional = false)
	@JoinColumn(name="fk_enterprise_id")
	private Enterprise enterprise;

	// Getters and Setters //-------------------------------------
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
}
