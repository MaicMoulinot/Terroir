package com.jomm.terroir.business;

import java.util.ArrayList;


public interface ProductServiceInterface {
	
	/**
	 * Save a product.
	 * @param product ProductEntity the product to save.
	 * @see com.jomm.terroir.business.ProductEntity
	 */
	public void persistProduct(ProductEntity product);
	
	/**
	 * Fetch the list of all products.
	 * @return a list of all products.
	 * @see com.jomm.terroir.business.ProductEntity
	 */
	public ArrayList<ProductEntity> getAllProducts();
	
	/**
	 * Delete a product.
	 * @param product ProductEntity.
	 * @see com.jomm.terroir.business.ProductEntity
	 */
	public void deleteProduct(ProductEntity product);
}
