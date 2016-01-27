package com.jomm.terroir.dao;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import javax.ejb.Stateless;
import javax.enterprise.inject.Alternative;

import com.jomm.terroir.business.SiteEntity;

@Stateless
@Alternative
public class SiteDaoStatic extends DaoStatic <Long, SiteEntity> implements SiteDaoInterface {

	private static Map<Long, SiteEntity> mapSites;

	/** Constructor with no parameter. */
	public SiteDaoStatic() {
		if (mapSites == null) {
			mapSites = new HashMap<Long, SiteEntity>();
		}
	}

	@Override
	public void persist(SiteEntity entity) {
		mapSites.put(entity.getId(), entity);
	}

	@Override
	public void remove(SiteEntity entity) {
		mapSites.remove(entity);
	}

	@Override
	public SiteEntity findById(Long id) {
		return mapSites.get(id);
	}

	@Override
	public Collection<SiteEntity> findAll() {
		return mapSites.values();
	}
}
