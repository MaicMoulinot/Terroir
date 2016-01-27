package com.jomm.terroir.dao;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import javax.ejb.Stateless;
import javax.enterprise.inject.Alternative;

import com.jomm.terroir.business.ProductEntity;

@Stateless
@Alternative
public class ProductDaoStatic extends DaoStatic <Long, ProductEntity> implements ProductDaoInterface {

	private static Map<Long, ProductEntity> listProducts;

	/** Constructor with no parameter. */
	public ProductDaoStatic() {
		if (listProducts == null) {
			listProducts = new HashMap<Long, ProductEntity>();
		}
	}

	@Override
	public void persist(ProductEntity entity) {
		listProducts.put(entity.getId(), entity);
	}

	@Override
	public void remove(ProductEntity entity) {
		listProducts.remove(entity);
	}

	@Override
	public ProductEntity findById(Long id) {
		return listProducts.get(id);
	}

	@Override
	public Collection<ProductEntity> findAll() {
		return listProducts.values();
	}
}
