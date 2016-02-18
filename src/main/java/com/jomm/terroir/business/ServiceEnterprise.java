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
	 * @throws NullPointerException if the entity is null.
	 * @throws IllegalStateException if the id is not null.
	 */
	public Enterprise create(Enterprise enterprise) throws NullPointerException, IllegalStateException;
	
	/**
	 * Update an enterprise.
	 * @param enterprise the {@link Enterprise} to update.
	 * @return the updated enterprise.
	 * @throws NullPointerException if the entity is null.
	 * @throws IllegalStateException if the id is null.
	 */
	public Enterprise update(Enterprise enterprise) throws NullPointerException, IllegalStateException;
	
	/**
	 * Fetch the list of all enterprises.
	 * @return a list of all {@link Enterprise}.
	 */
	public ArrayList<Enterprise> getAllEnterprises();
	
	/**
	 * Delete an enterprise.
	 * @param enterprise the {@link Enterprise} to delete.
	 * @throws NullPointerException if the entity is null.
	 * @throws IllegalStateException if the id is null.
	 */
	public void delete(Enterprise enterprise) throws NullPointerException, IllegalStateException;
}
