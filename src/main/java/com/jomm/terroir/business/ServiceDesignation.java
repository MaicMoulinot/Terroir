package com.jomm.terroir.business;

import java.math.BigDecimal;
import java.util.List;

import com.jomm.terroir.business.model.Designation;
import com.jomm.terroir.util.Constants.Unit;
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
	 * Fetch the {@link Designation} with the provided identifier, and 
	 * load its {@link com.jomm.terroir.business.model.Label}s' list.
	 * @param id Long the unique identifier.
	 * @return the {@link Designation}, or {@code null} if it was not found.
	 * @throws ExceptionService when the {@code id} is {@code null}.
	 */
	Designation getDesignation(Long id) throws ExceptionService;
	
	/**
	 * Delete an designation.
	 * @param designation the {@link Designation} to delete.
	 * @throws ExceptionService when the entity is not in a valid state.
	 */
	void delete(Designation designation) throws ExceptionService;
	
	/**
	 * Calculate the minimum price a product of {@link Designation} can be sold.
	 * @param designation the reference {@link Designation}.
	 * @return {@link BigDecimal} the computed minimum price.
	 * @throws ExceptionService when the entity is not in a valid state. 
	 */
	public BigDecimal getMinRangePrice(Designation designation) throws ExceptionService;
	
	/**
	 * Calculate the maximum price a product of {@link Designation} can be sold.
	 * @param designation the reference {@link Designation}.
	 * @return {@link BigDecimal} the computed maximum price.
	 * @throws ExceptionService when the entity is not in a valid state. 
	 */
	public BigDecimal getMaxRangePrice(Designation designation) throws ExceptionService;
	
	/**
	 * Convert the current price per unit into the designation's price per unit if feasible. 
	 * If the {@code currentUnit} and the {@code designation}'s unit are the same, it returns {@code pricePerUnit}.
	 * If the conversion is not possible, it returns {@code null}.
	 * @param designation {@link Designation} the designation.
	 * @param pricePerUnit {@link BigDecimal} the current price per unit.
	 * @param currentUnit {@link Unit} the current unit.
	 * @return a {@link BigDecimal} the corrected current price per unit.
	 * @throws ExceptionService when the entity is not in a valid state.
	 * @throws NullPointerException when either {@code currentUnit} or {@code currentUnit} is {@code null}.
	 */
	public BigDecimal convertPriceIntoDesignationUnit(Designation designation, BigDecimal pricePerUnit, 
			Unit currentUnit) throws ExceptionService;
}