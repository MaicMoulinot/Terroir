package com.jomm.terroir.business.model;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import com.jomm.terroir.business.ServiceUser;

/**
 * This Class is an {@link Entity} representing a seller.
 * It is a child of {@link AbstractUser}, so it uses {@link ServiceUser} for all its logic operations.
 * It implements {@link Serializable} and has a default serial version ID.
 * It includes an {@link Enterprise}.
 * Its properties and those from its parent {@link AbstractUser} are persisted in table "tr_seller".
 * @author Maic
 */
@Entity
@Table(name="tr_seller")
@NamedQuery(name="Seller.findAll", query="SELECT s FROM Seller s")
public class Seller extends AbstractUser implements Serializable {
	
	/** Serial version ID. Do not modify unless the type undergoes structural changes affecting serialization. */
	private static final long serialVersionUID = 1L;
	
	// Attributes
	@NotNull
	@ManyToOne(optional = false)
	private Enterprise enterprise;

	// Getters and Setters
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
