package com.jomm.terroir.business;

import java.util.ArrayList;

/**
 * This Interface describes all logic operations for {@link EnterpriseEntity}.
 * @author Maic
 */
public interface EnterpriseService {
	
	/**
	 * Create an enterprise.
	 * @param enterprise the {@link EnterpriseEntity} to create.
	 */
	public void create(EnterpriseEntity enterprise);
	
	/**
	 * Update an enterprise.
	 * @param enterprise the {@link EnterpriseEntity} to update.
	 */
	public void update(EnterpriseEntity enterprise);
	
	/**
	 * Fetch the list of all enterprises.
	 * @return a list of all {@link EnterpriseEntity}.
	 */
	public ArrayList<EnterpriseEntity> getAllEnterprises();
	
	/**
	 * Delete an enterprise.
	 * @param enterprise the {@link EnterpriseEntity} to delete.
	 */
	public void delete(EnterpriseEntity enterprise);
}
