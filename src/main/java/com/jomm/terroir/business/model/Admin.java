package com.jomm.terroir.business.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

import com.jomm.terroir.business.ServiceUser;

/**
 * This Class is an {@link Entity} representing an administrator of the system.
 * It is a child of {@link AbstractUser}, so it uses {@link ServiceUser} for all its logic operations.
 * It implements {@link Serializable} and has a generated serial version ID.
 * It includes administrative abilities that are invalid by default.
 * Its properties and those from its parent {@link AbstractUser} are persisted in table "tr_admin".
 * @author Maic
 */
@Entity
@Table(name="tr_admin")
@NamedQuery(name="Admin.findAll", query="SELECT a FROM Admin a")
public class Admin extends AbstractUser implements Serializable {
	
	/** Generated serial version ID. Do not modify. */
	private static final long serialVersionUID = -5225752296197017138L;

	// Attributes
	@Column(name = "can_read")
	private boolean canReadData;
	
	@Column(name = "can_update")
	private boolean canUpdateData;
	
	@Column(name = "can_delete")
	private boolean canDeleteData;

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
	public boolean canReadData() {
		return canReadData;
	}

	/**
	 * @param canReadData the canReadData to set
	 */
	public void setCanReadData(boolean canReadData) {
		this.canReadData = canReadData;
	}

	/**
	 * @return the canUpdateData
	 */
	public boolean canUpdateData() {
		return canUpdateData;
	}

	/**
	 * @param canUpdateData the canUpdateData to set
	 */
	public void setCanUpdateData(boolean canUpdateData) {
		this.canUpdateData = canUpdateData;
	}

	/**
	 * @return the canDeleteData
	 */
	public boolean canDeleteData() {
		return canDeleteData;
	}

	/**
	 * @param canDeleteData the canDeleteData to set
	 */
	public void setCanDeleteData(boolean canDeleteData) {
		this.canDeleteData = canDeleteData;
	}
}
