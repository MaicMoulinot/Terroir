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

	private static Map<Long, ProductEntity> mapProducts;

	/** Constructor with no parameter. */
	public ProductDaoStatic() {
		if (mapProducts == null) {
			mapProducts = new HashMap<Long, ProductEntity>();
		}
	}

	@Override
	public void persist(ProductEntity entity) {
		mapProducts.put(entity.getId(), entity);
	}

	@Override
	public void remove(ProductEntity entity) {
		mapProducts.remove(entity);
	}

	@Override
	public ProductEntity findById(Long id) {
		return mapProducts.get(id);
	}

	@Override
	public Collection<ProductEntity> findAll() {
		return mapProducts.values();
	}
}
