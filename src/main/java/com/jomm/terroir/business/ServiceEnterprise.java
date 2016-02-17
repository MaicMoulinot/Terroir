package com.jomm.terroir.business;

import java.util.ArrayList;

import com.jomm.terroir.business.model.Enterprise;
import com.jomm.terroir.util.InvalidEntityException;

/**
 * This Interface describes all logic operations for {@link Enterprise}.
 * @author Maic
 */
public interface ServiceEnterprise {
	
	/**
	 * Create an enterprise.
	 * @param enterprise the {@link Enterprise} to create.
	 * @return the persisted enterprise.
	 * @throws InvalidEntityException if the enterprise is null, or if the id is not null.
	 */
	public Enterprise create(Enterprise enterprise) throws InvalidEntityException;
	
	/**
	 * Update an enterprise.
	 * @param enterprise the {@link Enterprise} to update.
	 * @return the updated enterprise.
	 * @throws InvalidEntityException if the enterprise is null, or if the id is null.
	 */
	public Enterprise update(Enterprise enterprise) throws InvalidEntityException;
	
	/**
	 * Fetch the list of all enterprises.
	 * @return a list of all {@link Enterprise}.
	 */
	public ArrayList<Enterprise> getAllEnterprises();
	
	/**
	 * Delete an enterprise.
	 * @param enterprise the {@link Enterprise} to delete.
	 * @throws InvalidEntityException if the enterprise is null, or if the id is null.
	 */
	public void delete(Enterprise enterprise) throws InvalidEntityException;
}
