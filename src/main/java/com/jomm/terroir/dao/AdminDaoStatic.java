package com.jomm.terroir.dao;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import javax.ejb.Stateless;
import javax.enterprise.inject.Alternative;

import com.jomm.terroir.business.AdminEntity;

@Stateless
@Alternative
public class AdminDaoStatic extends DaoStatic <Long, AdminEntity> implements AdminDaoInterface {

	private static Map<Long, AdminEntity> mapAdmins;

	/** Constructor with no parameter. */
	public AdminDaoStatic() {
		if (mapAdmins == null) {
			mapAdmins = new HashMap<Long, AdminEntity>();
		}
	}

	@Override
	public void persist(AdminEntity entity) {
		mapAdmins.put(entity.getId(), entity);
	}

	@Override
	public void remove(AdminEntity entity) {
		mapAdmins.remove(entity);
	}

	@Override
	public AdminEntity findById(Long id) {
		return mapAdmins.get(id);
	}

	@Override
	public Collection<AdminEntity> findAll() {
		return mapAdmins.values();
	}
}
