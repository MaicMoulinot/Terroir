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

	private static Map<Long, EnterpriseEntity> listCompanies;

	/** Constructor with no parameter. */
	public EnterpriseDaoStatic() {
		if (listCompanies == null) {
			listCompanies = new HashMap<Long, EnterpriseEntity>();
		}
	}

	@Override
	public void persist(EnterpriseEntity entity) {
		listCompanies.put(entity.getId(), entity);
	}

	@Override
	public void remove(EnterpriseEntity entity) {
		listCompanies.remove(entity);
	}

	@Override
	public EnterpriseEntity findById(Long id) {
		return listCompanies.get(id);
	}

	@Override
	public Collection<EnterpriseEntity> findAll() {
		return listCompanies.values();
	}
}
