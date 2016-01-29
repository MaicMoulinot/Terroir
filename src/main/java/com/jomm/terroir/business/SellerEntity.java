package com.jomm.terroir.business;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

/**
 * This Class is an Entity representing a seller.
 * It is a child of {@link UserEntity}, so it uses {@link UserService} for all its logic operations.
 * It implements {@link Serializable} and has a generated serial version ID.
 * It includes an {@link EnterpriseEntity}.
 * Its properties and those from its parent {@link UserEntity} are persisted in table "tr_seller".
 * @author Maic
 */
@Entity
@Table(name="tr_seller")
@NamedQuery(name="SellerEntity.findAll", query="SELECT s FROM SellerEntity s")
public class SellerEntity extends UserEntity implements Serializable {
	
	/** Generated serial version ID. Do not modify. */
	private static final long serialVersionUID = 20497943860676595L;
	
	// Attributes
	@NotNull
	@ManyToOne
	private EnterpriseEntity enterprise;

	// Getters and Setters
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
