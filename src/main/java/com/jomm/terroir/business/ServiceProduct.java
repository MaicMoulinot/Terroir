package com.jomm.terroir.business;

import java.util.List;

import com.jomm.terroir.business.model.Product;
import com.jomm.terroir.util.exception.ExceptionInvalidId;
import com.jomm.terroir.util.exception.ExceptionNullEntity;

/**
 * This Interface describes all logic operations for {@link Product}.
 * @author Maic
 */
public interface ServiceProduct {
	
	/**
	 * Create a product and generate its {@code registrationDate}.
	 * @param product the {@link Product} to create.
	 * @return the persisted product.
	 * @throws ExceptionNullEntity if the entity is null.
	 * @throws ExceptionInvalidId if the id is not null.
	 */
	Product create(Product product) throws ExceptionNullEntity, ExceptionInvalidId;
	
	/**
	 * Update a product.
	 * @param product the {@link Product} to update.
	 * @return the updated product.
	 * @throws ExceptionNullEntity if the entity is null.
	 * @throws ExceptionInvalidId if the id is null.
	 */
	Product update(Product product) throws ExceptionNullEntity, ExceptionInvalidId;
	
	/**
	 * Fetch the list of all products.
	 * @return a list of all {@link Product}.
	 */
	List<Product> getAllProducts();
	
	/**
	 * Delete a product.
	 * @param product the {@link Product} to delete.
	 * @throws ExceptionNullEntity if the entity is null.
	 * @throws ExceptionInvalidId if the id is null.
	 */
	void delete(Product product) throws ExceptionNullEntity, ExceptionInvalidId;
}
