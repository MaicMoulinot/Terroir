package com.jomm.terroir.business;

import static com.jomm.terroir.util.exception.ExceptionService.TypeException.ENTITY_NULL;
import static com.jomm.terroir.util.exception.ExceptionService.TypeException.ID_NOT_NULL;
import static com.jomm.terroir.util.exception.ExceptionService.TypeException.ID_NULL;

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
		if (areEntitiesNull(product, product.getStock())) {
			throw new ExceptionService(ENTITY_NULL);
		} else if (product.getId() != null || product.getStock().getId() != null) {
			throw new ExceptionService(ID_NOT_NULL);
		}
		product.getStock().setLastUpdate(ZonedDateTime.now());
		return daoProduct.create(product);
	}

	@Override
	public Product update(Product product) throws ExceptionService {
		if (areEntitiesNull(product, product.getStock())) {
			throw new ExceptionService(ENTITY_NULL);
		} else if (product.getId() == null || product.getStock().getId() == null) {
			throw new ExceptionService(ID_NULL);
		}
		return daoProduct.update(product);
	}

	@Override
	public Stock updateQuantity(Stock stock, Integer newQuantity) throws ExceptionService {
		if (areEntitiesNull(stock.getProduct(), stock)) {
			throw new ExceptionService(ENTITY_NULL);
		} else if (stock.getId() == null || stock.getProduct().getId() == null) {
			throw new ExceptionService(ID_NULL);
		} else if (newQuantity == null || newQuantity < 0) {
			throw new ExceptionService(ID_NOT_NULL);//TODO INTEGER
		}
		stock.setQuantity(newQuantity);
		stock.setLastUpdate(ZonedDateTime.now());
		if (!updateProduct(newQuantity, stock.getProduct())) {
			// Update only Stock
			daoStock.update(stock);
		}
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
			throw new ExceptionService(ENTITY_NULL);
		} else if (product.getId() == null) {
			throw new ExceptionService(ID_NULL);
		}
		daoProduct.delete(product);
	}
	
	// Helpers //-------------------------------------------------	
	/**
	 * Verify if at least one of the entities is {@code null}.
	 * @param product the {@link Product}.
	 * @param stock the {@link Stock}.
	 * @return {@code true} if at least one of the entities is {@code null}, {@code false} otherwise.
	 */
	private boolean areEntitiesNull(Product product, Stock stock) {
		return product == null || stock == null;
	}
	
	/**
	 * Update a product's availability, if necessary, with a call to {@link Product#setActive(Boolean)}.
	 * @param newQuantity {@link Integer} the new quantity.
	 * @param product the {@link Product}.
	 * @return {@code true} if the {@link Product} was updated, {@code false} otherwise.
	 * @throws ExceptionService.
	 */
	private boolean updateProduct(Integer newQuantity, Product product) throws ExceptionService {
		boolean productUpdated = false;
		if (newQuantity == 0 && product.isActive()) {
			// If stock's new quantity is zero, the product should not be active for sale
			product.setActive(false);
			productUpdated = true;
		} else if (newQuantity > 0 && !product.isActive()) {
			// If stock's new quantity is > 0, the product should be active for sale
			product.setActive(true);
			productUpdated = true;
		}
		if (productUpdated) {
			// Update both Product + Stock
			update(product);
		}
		return productUpdated;
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