package com.jomm.terroir.business.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

import com.jomm.terroir.business.ServiceUser;

/**
 * This Class is an {@link Entity} representing an administrator of the system.
 * It extends {@link AbstractUser}, thus it indirectly implements 
 * {@link java.io.Serializable} and has a default serial version ID.
 * It uses {@link ServiceUser} for all its business operations.
 * It includes administrative abilities that are invalid by default.
 * Its properties and those from its parent {@link AbstractUser} are persisted in table {@code tr_admin}.
 * @author Maic
 */
@Entity
@Table(name="tr_admin")
@NamedQuery(name="Admin.findAll", query="SELECT a FROM Admin a")
public class Admin extends AbstractUser {
	
	/** Serial version ID. Do not modify unless the type undergoes structural changes affecting serialization. */
	private static final long serialVersionUID = 1L;

	// Attributes
	@Column(name = "can_read")
	private Boolean canReadData;
	
	@Column(name = "can_update")
	private Boolean canUpdateData;
	
	@Column(name = "can_delete")
	private Boolean canDeleteData;

	/**
	 * No-arg constructor.
	 * Default is no special power.
	 */
	public Admin() {
		super();
		canReadData = false;
		canUpdateData = false;
		canDeleteData = false;
	}

	// Getters and Setters
	/**
	 * @return the canReadData
	 */
	public Boolean canReadData() {
		return canReadData;
	}

	/**
	 * @param canReadData the canReadData to set
	 */
	public void setCanReadData(Boolean canReadData) {
		this.canReadData = canReadData;
	}

	/**
	 * @return the canUpdateData
	 */
	public Boolean canUpdateData() {
		return canUpdateData;
	}

	/**
	 * @param canUpdateData the canUpdateData to set
	 */
	public void setCanUpdateData(Boolean canUpdateData) {
		this.canUpdateData = canUpdateData;
	}

	/**
	 * @return the canDeleteData
	 */
	public Boolean canDeleteData() {
		return canDeleteData;
	}

	/**
	 * @param canDeleteData the canDeleteData to set
	 */
	public void setCanDeleteData(Boolean canDeleteData) {
		this.canDeleteData = canDeleteData;
	}
}