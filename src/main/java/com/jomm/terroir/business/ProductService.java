package com.jomm.terroir.business;

import java.util.ArrayList;

/**
 * This Interface describes all logic operations for {@link ProductEntity}.
 * @author Maic
 */
public interface ProductService {
	
	/**
	 * Create a product.
	 * @param product the {@link ProductEntity} to create.
	 */
	public void create(ProductEntity product);
	
	/**
	 * Update a product.
	 * @param product the {@link ProductEntity} to update.
	 */
	public void update(ProductEntity product);
	
	/**
	 * Fetch the list of all products.
	 * @return a list of all {@link ProductEntity}.
	 */
	public ArrayList<ProductEntity> getAllProducts();
	
	/**
	 * Delete a product.
	 * @param product the {@link ProductEntity} to delete.
	 */
	public void delete(ProductEntity product);
}
