package com.jomm.terroir.business;

import java.util.ArrayList;

/**
 * This Interface describes all logic operations for {@link Product}.
 * @author Maic
 */
public interface ProductService {
	
	/**
	 * Create a product.
	 * @param product the {@link Product} to create.
	 * @return the persisted product.
	 */
	public Product create(Product product);
	
	/**
	 * Update a product.
	 * @param product the {@link Product} to update.
	 * @return the updated product.
	 */
	public Product update(Product product);
	
	/**
	 * Fetch the list of all products.
	 * @return a list of all {@link Product}.
	 */
	public ArrayList<Product> getAllProducts();
	
	/**
	 * Delete a product.
	 * @param product the {@link Product} to delete.
	 */
	public void delete(Product product);
}
