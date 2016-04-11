package com.jomm.terroir.business;

import java.util.List;

import com.jomm.terroir.business.model.Enterprise;
import com.jomm.terroir.util.exception.ExceptionService;

/**
 * This Interface describes all logic operations for {@link Enterprise}.
 * @author Maic
 */
public interface ServiceEnterprise {
	
	/**
	 * Create an enterprise and calls {@link Enterprise#setRegistrationDate(java.time.ZonedDateTime)}.
	 * @param enterprise the {@link Enterprise} to create.
	 * @return the persisted enterprise.
	 * @throws ExceptionService when the entity is not in a valid state.
	 */
	Enterprise create(Enterprise enterprise) throws ExceptionService;
	
	/**
	 * Update an enterprise.
	 * @param enterprise the {@link Enterprise} to update.
	 * @return the updated enterprise.
	 * @throws ExceptionService when the entity is not in a valid state.
	 */
	Enterprise update(Enterprise enterprise) throws ExceptionService;
	
	/**
	 * Fetch the list of all enterprises.
	 * @return a list of all {@link Enterprise}.
	 */
	List<Enterprise> getAllEnterprises();
	
	/**
	 * Fetch the {@link Enterprise} with the provided identifier.
	 * @param id Long the unique identifier.
	 * @return the {@link Enterprise}, or {@code null} if it was not found.
	 * @throws ExceptionService when the {@code id} is {@code null}.
	 */
	Enterprise getEnterprise(Long id) throws ExceptionService;
	
	/**
	 * Delete an enterprise.
	 * @param enterprise the {@link Enterprise} to delete.
	 * @throws ExceptionService when the entity is not in a valid state.
	 */
	void delete(Enterprise enterprise) throws ExceptionService;
}
