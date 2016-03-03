package com.jomm.terroir.business;

import java.time.ZonedDateTime;
import java.util.List;

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
	
	@Inject
	private DaoProduct daoProduct;

	@Override
	public Product create(Product product) throws ExceptionNullEntity, ExceptionInvalidId {
		if (product == null) {
			throw new ExceptionNullEntity();
		} else if (product.getId() != null) {
			throw new ExceptionInvalidId(true);
		}
		product.setRegistrationDate(ZonedDateTime.now());
		return daoProduct.create(product);
	}
	
	@Override
	public Product update(Product product) throws ExceptionNullEntity, ExceptionInvalidId {
		if (product == null) {
			throw new ExceptionNullEntity();
		} else if (product.getId() == null) {
			throw new ExceptionInvalidId(false);
		}
		return daoProduct.update(product);
	}

	@Override
	@TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
	public List<Product> getAllProducts() {
		return daoProduct.findAll();
	}
	
	@Override
	public void delete(Product product) throws ExceptionNullEntity, ExceptionInvalidId {
		if (product == null) {
			throw new ExceptionNullEntity();
		} else if (product.getId() == null) {
			throw new ExceptionInvalidId(false);
		}
		daoProduct.delete(product);
	}
	
	/**
	 * This method should only be used in tests, so the visibility is set to default/package.
	 * @param daoProduct the daoProduct to set.
	 */
	void setDaoProduct(DaoProduct daoProduct) {
		this.daoProduct = daoProduct;
	}
}