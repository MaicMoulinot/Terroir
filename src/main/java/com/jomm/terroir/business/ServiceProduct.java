package com.jomm.terroir.business;

import java.util.ArrayList;

import com.jomm.terroir.business.model.Product;

/**
 * This Interface describes all logic operations for {@link Product}.
 * @author Maic
 */
public interface ServiceProduct {
	
	/**
	 * Create a product and generate its <code>registrationDate</code>.
	 * @param product the {@link Product} to create.
	 * @return the persisted product.
	 * @throws NullPointerException if the entity is null.
	 * @throws IllegalArgumentException if the id is not null.
	 */
	Product create(Product product) throws NullPointerException, IllegalArgumentException;
	
	/**
	 * Update a product.
	 * @param product the {@link Product} to update.
	 * @return the updated product.
	 * @throws NullPointerException if the entity is null.
	 * @throws IllegalArgumentException if the id is null.
	 */
	Product update(Product product) throws NullPointerException, IllegalArgumentException;
	
	/**
	 * Fetch the list of all products.
	 * @return a list of all {@link Product}.
	 */
	ArrayList<Product> getAllProducts();
	
	/**
	 * Delete a product.
	 * @param product the {@link Product} to delete.
	 * @throws NullPointerException if the entity is null.
	 * @throws IllegalArgumentException if the id is null.
	 */
	void delete(Product product) throws NullPointerException, IllegalArgumentException;
}
