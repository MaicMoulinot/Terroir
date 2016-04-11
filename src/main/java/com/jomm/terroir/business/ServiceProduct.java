package com.jomm.terroir.business;

import java.util.List;

import com.jomm.terroir.business.model.Product;
import com.jomm.terroir.business.model.Stock;
import com.jomm.terroir.util.exception.ExceptionService;

/**
 * This Interface describes all logic operations for {@link Product} 
 * and its associated {@link com.jomm.terroir.business.model.Stock}.
 * @author Maic
 */
public interface ServiceProduct {
	
	/**
	 * Create a product and its stock and set its stock's last update time with a call to 
	 * {@link com.jomm.terroir.business.model.Stock#setLastUpdate(java.time.ZonedDateTime)}.
	 * @param product the {@link Product} to create.
	 * @return the persisted product.
	 * @throws ExceptionService when the entity is not in a valid state.
	 */
	Product create(Product product) throws ExceptionService;
	
	/**
	 * Update a product.
	 * @param product the {@link Product} to update.
	 * @return the updated product.
	 * @throws ExceptionService when the entity is not in a valid state.
	 */
	Product update(Product product) throws ExceptionService;
	
	/**
	 * Update a stock's availability and set its last update time with a call to  
	 * {@link Stock#setLastUpdate(java.time.ZonedDateTime)}.
	 * @param stock the {@link Stock} to update.
	 * @param newQuantity {@link Integer} the new quantity. Must not be {@code null}, and must be positive.
	 * @return the updated stock.
	 * @throws ExceptionService when the entity is not in a valid state.
	 */
	Stock updateAvailability(Stock stock, Integer newQuantity) throws ExceptionService;
	
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
	
	/**
	 * Fetch the {@link Product} with the provided identifier.
	 * @param id Long the unique identifier.
	 * @return the {@link Product}, or {@code null} if it was not found.
	 * @throws ExceptionService when the {@code id} is {@code null}.
	 */
	Product getProduct(Long id) throws ExceptionService;
	
	/**
	 * Fetch the {@link Stock} with the provided identifier.
	 * @param id Long the unique identifier.
	 * @return the {@link Stock}, or {@code null} if it was not found.
	 * @throws ExceptionService when the {@code id} is {@code null}.
	 */
	Stock getStock(Long id) throws ExceptionService;
}