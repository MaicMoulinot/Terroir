package com.jomm.terroir.business;

import java.util.ArrayList;

import com.jomm.terroir.business.model.Enterprise;

/**
 * This Interface describes all logic operations for {@link Enterprise}.
 * @author Maic
 */
public interface ServiceEnterprise {
	
	/**
	 * Create an enterprise.
	 * @param enterprise the {@link Enterprise} to create.
	 * @return the persisted enterprise.
	 */
	public Enterprise create(Enterprise enterprise);
	
	/**
	 * Update an enterprise.
	 * @param enterprise the {@link Enterprise} to update.
	 * @return the updated enterprise.
	 */
	public Enterprise update(Enterprise enterprise);
	
	/**
	 * Fetch the list of all enterprises.
	 * @return a list of all {@link Enterprise}.
	 */
	public ArrayList<Enterprise> getAllEnterprises();
	
	/**
	 * Delete an enterprise.
	 * @param enterprise the {@link Enterprise} to delete.
	 */
	public void delete(Enterprise enterprise);
}
