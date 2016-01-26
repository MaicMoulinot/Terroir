package com.jomm.terroir.dao;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import javax.ejb.Stateless;
import javax.enterprise.inject.Alternative;

import com.jomm.terroir.business.EstablishmentEntity;

@Stateless
@Alternative
public class EstablishmentDaoStatic extends DaoStatic <Long, EstablishmentEntity> implements EstablishmentDaoInterface {

	private static Map<Long, EstablishmentEntity> listEtablishments;

	/** Constructor with no parameter. */
	public EstablishmentDaoStatic() {
		if (listEtablishments == null) {
			listEtablishments = new HashMap<Long, EstablishmentEntity>();
		}
	}

	@Override
	public void persist(EstablishmentEntity entity) {
		listEtablishments.put(entity.getId(), entity);
	}

	@Override
	public void remove(EstablishmentEntity entity) {
		listEtablishments.remove(entity);
	}

	@Override
	public EstablishmentEntity findById(Long id) {
		return listEtablishments.get(id);
	}

	@Override
	public Collection<EstablishmentEntity> findAll() {
		return listEtablishments.values();
	}
}
