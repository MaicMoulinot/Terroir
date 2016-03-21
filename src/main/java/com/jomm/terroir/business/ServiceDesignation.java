package com.jomm.terroir.business;

import java.util.List;

import com.jomm.terroir.business.model.Designation;
import com.jomm.terroir.util.exception.ExceptionService;

/**
 * This Interface describes all logic operations for {@link Designation}.
 * @author Maic
 */
public interface ServiceDesignation {
	
	/**
	 * Create an designation.
	 * @param designation the {@link Designation} to create.
	 * @return the persisted designation.
	 * @throws ExceptionService when the entity is not in a valid state.
	 */
	Designation create(Designation designation) throws ExceptionService;
	
	/**
	 * Update an designation.
	 * @param designation the {@link Designation} to update.
	 * @return the updated designation.
	 * @throws ExceptionService when the entity is not in a valid state.
	 */
	Designation update(Designation designation) throws ExceptionService;
	
	/**
	 * Fetch the list of all designations.
	 * @return a list of all {@link Designation}.
	 */
	List<Designation> getAllDesignations();
	
	/**
	 * Delete an designation.
	 * @param designation the {@link Designation} to delete.
	 * @throws ExceptionService when the entity is not in a valid state.
	 */
	void delete(Designation designation) throws ExceptionService;
}
