package com.jomm.terroir.business;

import static com.jomm.terroir.util.Constants.ResourceBundleError.ENTITY_SHOULD_NOT_BE_NULL;
import static com.jomm.terroir.util.Constants.ResourceBundleError.ID_SHOULD_BE_NULL;
import static com.jomm.terroir.util.Constants.ResourceBundleError.ID_SHOULD_NOT_BE_NULL;
import static com.jomm.terroir.util.Constants.ResourceBundleError.INTEGER;

import java.time.ZonedDateTime;
import java.util.List;

import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.inject.Inject;

import com.jomm.terroir.business.model.Product;
import com.jomm.terroir.business.model.Stock;
import com.jomm.terroir.dao.DaoProduct;
import com.jomm.terroir.dao.DaoStock;
import com.jomm.terroir.util.exception.ExceptionService;

/**
 * This Class is the Service relating to {@link Product} and {@link Stock}.
 * It implements {@link ServiceProduct} and defines all its business methods.
 * It relates to {@link DaoProduct} and {@link DaoStock} for all persistence operations.
 * @author Maic
 */
@Stateless
public class ServiceProductImpl implements ServiceProduct {

	// Injected Fields //-----------------------------------------
	@Inject
	private DaoProduct daoProduct;
	@Inject
	private DaoStock daoStock;

	// Methods //-------------------------------------------------
	@Override
	public Product create(Product product) throws ExceptionService {
		if (product == null || product.getStock() == null) {
			throw new ExceptionService(ENTITY_SHOULD_NOT_BE_NULL);
		} else if (product.getId() != null || product.getStock().getId() != null) {
			throw new ExceptionService(ID_SHOULD_BE_NULL);
		}
		product.getStock().setLastUpdate(ZonedDateTime.now());
		return daoProduct.create(product);
	}

	@Override
	public Product update(Product product) throws ExceptionService {
		if (product == null || product.getStock() == null) {
			throw new ExceptionService(ENTITY_SHOULD_NOT_BE_NULL);
		} else if (product.getId() == null || product.getStock().getId() == null) {
			throw new ExceptionService(ID_SHOULD_NOT_BE_NULL);
		}
		return daoProduct.update(product);
	}

	@Override
	public Stock updateQuantity(Stock stock, Integer newQuantity) throws ExceptionService {
		if (stock == null || stock.getProduct() == null) {
			throw new ExceptionService(ENTITY_SHOULD_NOT_BE_NULL);
		} else if (stock.getId() == null || stock.getProduct().getId() == null) {
			throw new ExceptionService(ID_SHOULD_NOT_BE_NULL);
		} else if (newQuantity == null || newQuantity < 0) {
			throw new ExceptionService(INTEGER);
		}
		stock.setQuantity(newQuantity);
		stock.setLastUpdate(ZonedDateTime.now());
		updateStock(newQuantity, stock);
		return stock;
	}
	
	@Override
	@TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
	public List<Product> getAllProducts() {
		return daoProduct.findAll();
	}

	@Override
	public void delete(Product product) throws ExceptionService {
		if (product == null) {
			throw new ExceptionService(ENTITY_SHOULD_NOT_BE_NULL);
		} else if (product.getId() == null) {
			throw new ExceptionService(ID_SHOULD_NOT_BE_NULL);
		}
		daoProduct.delete(product);
	}
	
	// Helpers //-------------------------------------------------	
	/**
	 * Update the Stock, and its product's availability if necessary, with a call to {@link Product#setActive(Boolean)}.
	 * @param newQuantity {@link Integer} the new quantity.
	 * @param stock the {@link Stock}.
	 * @throws ExceptionService.
	 */
	private void updateStock(Integer newQuantity, Stock stock) throws ExceptionService {
		Product product = stock.getProduct();
		if (newQuantity == 0 && product.isActive()) {
			// If stock's new quantity is zero, the product should not be active for sale
			product.setActive(false);
			// Update both Product + Stock
			update(product);
		} else if (newQuantity > 0 && !product.isActive()) {
			// If stock's new quantity is > 0, the product should be active for sale
			product.setActive(true);
			// Update both Product + Stock
			update(product);
		} else {
			// Update only Stock
			daoStock.update(stock);
		}
	}
	
	// Tests only //----------------------------------------------
	/**
	 * This method should only be used in tests, so the visibility is set to default/package.
	 * @param daoProduct the daoProduct to set.
	 */
	void setTestDaoProduct(DaoProduct daoProduct) {
		this.daoProduct = daoProduct;
	}

	/**
	 * This method should only be used in tests, so the visibility is set to default/package.
	 * @param daoStock the daoStock to set.
	 */
	void setTestDaoStock(DaoStock daoStock) {
		this.daoStock = daoStock;
	}
}