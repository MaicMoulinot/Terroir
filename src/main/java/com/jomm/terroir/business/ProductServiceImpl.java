package com.jomm.terroir.business;

import java.util.ArrayList;

import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.inject.Inject;

import com.jomm.terroir.dao.ProductDao;

/**
 * This Class is the Service relating to {@link ProductEntity}.
 * It implements {@link ProductService} and defines all its business methods.
 * It relates to {@link ProductDao} for all persistence operations.
 * @author Maic
 */
@Stateless
public class ProductServiceImpl implements ProductService {
	
	@Inject
	private ProductDao productDao;

	@Override
	public void create(ProductEntity product) {
		productDao.create(product);
	}
	
	@Override
	public void update(ProductEntity product) {
		productDao.update(product);
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
	public void delete(ProductEntity product) {
		productDao.delete(product);
	}

	/**
	 * This method is used for Junit testing only.
	 * @param productDao the productDao to set
	 */
	void setUserDao(ProductDao productDao) {
		this.productDao = productDao;
	}
}
