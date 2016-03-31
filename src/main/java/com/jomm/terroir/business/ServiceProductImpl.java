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
import com.jomm.terroir.dao.DaoProduct;
import com.jomm.terroir.util.exception.ExceptionService;

/**
 * This Class is the Service relating to {@link Product}.
 * It implements {@link ServiceProduct} and defines all its business methods.
 * It relates to {@link DaoProduct} for all persistence operations.
 * @author Maic
 */
@Stateless
public class ServiceProductImpl implements ServiceProduct {
	
	// Injected Fields //-----------------------------------------
	@Inject
	private DaoProduct daoProduct;
	
	// Methods //-------------------------------------------------
	@Override
	public Product create(Product product) throws ExceptionService {
		if (product == null) {
			throw new ExceptionService(ENTITY_NULL);
		} else if (product.getId() != null) {
			throw new ExceptionService(ID_NOT_NULL);
		}
		product.setLastUpdate(ZonedDateTime.now());
		return daoProduct.create(product);
	}
	
	@Override
	public Product update(Product product) throws ExceptionService {
		if (product == null) {
			throw new ExceptionService(ENTITY_NULL);
		} else if (product.getId() == null) {
			throw new ExceptionService(ID_NULL);
		}
		product.setLastUpdate(ZonedDateTime.now());
		return daoProduct.update(product);
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
	
	// Tests //---------------------------------------------------
	/**
	 * This method should only be used in tests, so the visibility is set to default/package.
	 * @param daoProduct the daoProduct to set.
	 */
	void setTestDaoProduct(DaoProduct daoProduct) {
		this.daoProduct = daoProduct;
	}
}