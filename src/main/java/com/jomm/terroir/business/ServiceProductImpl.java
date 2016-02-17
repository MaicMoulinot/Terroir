package com.jomm.terroir.business;

import java.util.ArrayList;

import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.inject.Inject;

import com.jomm.terroir.business.model.Product;
import com.jomm.terroir.dao.DaoProduct;

/**
 * This Class is the Service relating to {@link Product}.
 * It implements {@link ServiceProduct} and defines all its business methods.
 * It relates to {@link DaoProduct} for all persistence operations.
 * @author Maic
 */
@Stateless
public class ServiceProductImpl implements ServiceProduct {
	
	@Inject
	private DaoProduct productDao;

	@Override
	public Product create(Product product) {
		productDao.create(product);
		return product;
	}
	
	@Override
	public Product update(Product product) {
		productDao.update(product);
		return product;
	}

	@Override
	@TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
	public ArrayList<Product> getAllProducts() {
		ArrayList<Product> result = new ArrayList<>();
		for (Product product : productDao.findAll()) {
			result.add(product);
		}
		return result;
	}
	
	@Override
	public void delete(Product product) {
		productDao.delete(product);
	}

	/**
	 * This method is used for Junit testing only.
	 * @param productDao the productDao to set
	 */
	void setProductDao(DaoProduct productDao) {
		this.productDao = productDao;
	}
}