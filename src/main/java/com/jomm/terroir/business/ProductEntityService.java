package com.jomm.terroir.business;

import java.util.ArrayList;

import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.inject.Inject;

import com.jomm.terroir.dao.ProductDaoInterface;

@Stateless
public class ProductEntityService implements ProductEntityServiceInterface {
	
	@Inject
	private ProductDaoInterface productDao;

	@Override
	public void persistProduct(ProductEntity product) {
		// Call Service to persist
		productDao.persist(product);
	}

	@Override
	@TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
	public ArrayList<ProductEntity> getAllProducts() {
		ArrayList<ProductEntity> result = new ArrayList<>();
		for (ProductEntity product : productDao.findAll()) {
			result.add(product);
		}
		return result;
	}
	
	@Override
	public void deleteProduct(ProductEntity product) {
		// Call Service to remove
		productDao.remove(product);
	}

	/**
	 * This method is used for Junit testing only.
	 * @param productDao the productDao to set
	 */
	void setUserDao(ProductDaoInterface productDao) {
		this.productDao = productDao;
	}
}
