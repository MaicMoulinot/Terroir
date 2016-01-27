package com.jomm.terroir.dao;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import javax.ejb.Stateless;
import javax.enterprise.inject.Alternative;

import com.jomm.terroir.business.EnterpriseEntity;

@Stateless
@Alternative
public class EnterpriseDaoStatic extends DaoStatic <Long, EnterpriseEntity> implements EnterpriseDaoInterface {

	private static Map<Long, EnterpriseEntity> mapEnterprises;

	/** Constructor with no parameter. */
	public EnterpriseDaoStatic() {
		if (mapEnterprises == null) {
			mapEnterprises = new HashMap<Long, EnterpriseEntity>();
		}
	}

	@Override
	public void persist(EnterpriseEntity entity) {
		mapEnterprises.put(entity.getId(), entity);
	}

	@Override
	public void remove(EnterpriseEntity entity) {
		mapEnterprises.remove(entity);
	}

	@Override
	public EnterpriseEntity findById(Long id) {
		return mapEnterprises.get(id);
	}

	@Override
	public Collection<EnterpriseEntity> findAll() {
		return mapEnterprises.values();
	}
}
