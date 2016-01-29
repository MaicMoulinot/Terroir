package com.jomm.terroir.business;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

/**
 * This Class is an {@link Entity} representing a seller.
 * It is a child of {@link AbstractUser}, so it uses {@link UserService} for all its logic operations.
 * It implements {@link Serializable} and has a generated serial version ID.
 * It includes an {@link Enterprise}.
 * Its properties and those from its parent {@link AbstractUser} are persisted in table "tr_seller".
 * @author Maic
 */
@Entity
@Table(name="tr_seller")
@NamedQuery(name="Seller.findAll", query="SELECT s FROM Seller s")
public class Seller extends AbstractUser implements Serializable {
	
	/** Generated serial version ID. Do not modify. */
	private static final long serialVersionUID = 20497943860676595L;
	
	// Attributes
	@NotNull
	@ManyToOne
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
