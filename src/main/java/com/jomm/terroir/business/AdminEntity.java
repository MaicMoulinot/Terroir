package com.jomm.terroir.business;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.PrimaryKeyJoinColumn;

@Entity(name="admin")
@PrimaryKeyJoinColumn(name="ID", referencedColumnName="ID")
public class AdminEntity extends UserEntity {
	
	// Attributes
	@Column(name = "can_edit")
	private boolean canEditData;
	
	@Column(name = "can_update")
	private boolean canUpdateData;
	
	@Column(name = "can_delete")
	private boolean canDeleteData;

	// Getters and Setters
	/**
	 * @return the canEditData
	 */
	public boolean isCanEditData() {
		return canEditData;
	}

	/**
	 * @param canEditData the canEditData to set
	 */
	public void setCanEditData(boolean canEditData) {
		this.canEditData = canEditData;
	}

	/**
	 * @return the canUpdateData
	 */
	public boolean isCanUpdateData() {
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
	public boolean isCanDeleteData() {
		return canDeleteData;
	}

	/**
	 * @param canDeleteData the canDeleteData to set
	 */
	public void setCanDeleteData(boolean canDeleteData) {
		this.canDeleteData = canDeleteData;
	}
}
