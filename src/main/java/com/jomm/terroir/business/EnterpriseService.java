package com.jomm.terroir.business;

import java.util.ArrayList;

/**
 * This Interface describes all logic operations for {@link Enterprise}.
 * @author Maic
 */
public interface EnterpriseService {
	
	/**
	 * Create an enterprise.
	 * @param enterprise the {@link Enterprise} to create.
	 */
	public void create(Enterprise enterprise);
	
	/**
	 * Update an enterprise.
	 * @param enterprise the {@link Enterprise} to update.
	 */
	public void update(Enterprise enterprise);
	
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
