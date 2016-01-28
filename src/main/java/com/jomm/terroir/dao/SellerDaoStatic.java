package com.jomm.terroir.dao;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import javax.ejb.Stateless;
import javax.enterprise.inject.Alternative;

import com.jomm.terroir.business.SellerEntity;

@Stateless
@Alternative
public class SellerDaoStatic extends DaoStatic <Long, SellerEntity> implements SellerDaoInterface {

	private static Map<Long, SellerEntity> mapSellers;

	/** Constructor with no parameter. */
	public SellerDaoStatic() {
		if (mapSellers == null) {
			mapSellers = new HashMap<Long, SellerEntity>();
		}
	}

	@Override
	public void persist(SellerEntity entity) {
		mapSellers.put(entity.getId(), entity);
	}

	@Override
	public void remove(SellerEntity entity) {
		mapSellers.remove(entity);
	}

	@Override
	public SellerEntity findById(Long id) {
		return mapSellers.get(id);
	}

	@Override
	public Collection<SellerEntity> findAll() {
		return mapSellers.values();
	}
}
