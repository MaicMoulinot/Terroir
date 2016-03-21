package com.jomm.terroir.business;

import java.util.List;

import com.jomm.terroir.business.model.Product;
import com.jomm.terroir.util.exception.ExceptionService;

/**
 * This Interface describes all logic operations for {@link Product}.
 * @author Maic
 */
public interface ServiceProduct {
	
	/**
	 * Create a product and calls {@link Product#setLastUpdate(java.time.ZonedDateTime)}.
	 * @param product the {@link Product} to create.
	 * @return the persisted product.
	 * @throws ExceptionService when the entity is not in a valid state.
	 */
	Product create(Product product) throws ExceptionService;
	
	/**
	 * Update a product and calls {@link Product#setLastUpdate(java.time.ZonedDateTime)}.
	 * @param product the {@link Product} to update.
	 * @return the updated product.
	 * @throws ExceptionService when the entity is not in a valid state.
	 */
	Product update(Product product) throws ExceptionService;
	
	/**
	 * Fetch the list of all products.
	 * @return a list of all {@link Product}.
	 */
	List<Product> getAllProducts();
	
	/**
	 * Delete a product.
	 * @param product the {@link Product} to delete.
	 * @throws ExceptionService when the entity is not in a valid state.
	 */
	void delete(Product product) throws ExceptionService;
}
