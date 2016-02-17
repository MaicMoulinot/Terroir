package com.jomm.terroir.business;

import java.util.ArrayList;

import com.jomm.terroir.business.model.Product;
import com.jomm.terroir.util.InvalidEntityException;

/**
 * This Interface describes all logic operations for {@link Product}.
 * @author Maic
 */
public interface ServiceProduct {
	
	/**
	 * Create a product.
	 * @param product the {@link Product} to create.
	 * @return the persisted product.
	 * @throws NullPointerException if the entity is null.
	 * @throws InvalidEntityException if the id is not null.
	 */
	public Product create(Product product) throws NullPointerException, InvalidEntityException;
	
	/**
	 * Update a product.
	 * @param product the {@link Product} to update.
	 * @return the updated product.
	 * @throws NullPointerException if the entity is null.
	 * @throws InvalidEntityException if the id is null.
	 */
	public Product update(Product product) throws NullPointerException, InvalidEntityException;
	
	/**
	 * Fetch the list of all products.
	 * @return a list of all {@link Product}.
	 */
	public ArrayList<Product> getAllProducts();
	
	/**
	 * Delete a product.
	 * @param product the {@link Product} to delete.
	 * @throws NullPointerException if the entity is null.
	 */
	public void delete(Product product) throws NullPointerException;
}
