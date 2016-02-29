package com.jomm.terroir.business;

import java.time.ZonedDateTime;
import java.util.ArrayList;

import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.inject.Inject;

import com.jomm.terroir.business.model.Product;
import com.jomm.terroir.dao.DaoProduct;
import com.jomm.terroir.util.exception.ExceptionInvalidId;
import com.jomm.terroir.util.exception.ExceptionNullEntity;

/**
 * This Class is the Service relating to {@link Product}.
 * It implements {@link ServiceProduct} and defines all its business methods.
 * It relates to {@link DaoProduct} for all persistence operations.
 * @author Maic
 */
@Stateless
public class ServiceProductImpl implements ServiceProduct {
	
	// The visibility of DAO is set to default/package to be accessible in tests.
	@Inject
	DaoProduct productDao;

	@Override
	public Product create(Product product) throws ExceptionNullEntity, ExceptionInvalidId {
		if (product == null) {
			throw new ExceptionNullEntity();
		} else if (product.getId() != null) {
			throw new ExceptionInvalidId(true);
		}
		product.setRegistrationDate(ZonedDateTime.now());
		return productDao.create(product);
	}
	
	@Override
	public Product update(Product product) throws ExceptionNullEntity, ExceptionInvalidId {
		if (product == null) {
			throw new ExceptionNullEntity();
		} else if (product.getId() == null) {
			throw new ExceptionInvalidId(false);
		}
		return productDao.update(product);
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
	public void delete(Product product) throws ExceptionNullEntity, ExceptionInvalidId {
		if (product == null) {
			throw new ExceptionNullEntity();
		} else if (product.getId() == null) {
			throw new ExceptionInvalidId(false);
		}
		productDao.delete(product);
	}
}
