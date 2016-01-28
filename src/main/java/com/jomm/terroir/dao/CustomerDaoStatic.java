package com.jomm.terroir.dao;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import javax.ejb.Stateless;
import javax.enterprise.inject.Alternative;

import com.jomm.terroir.business.CustomerEntity;

@Stateless
@Alternative
public class CustomerDaoStatic extends DaoStatic <Long, CustomerEntity> implements CustomerDaoInterface {

	private static Map<Long, CustomerEntity> mapCustomers;

	/** Constructor with no parameter. */
	public CustomerDaoStatic() {
		if (mapCustomers == null) {
			mapCustomers = new HashMap<Long, CustomerEntity>();
		}
	}

	@Override
	public void persist(CustomerEntity entity) {
		mapCustomers.put(entity.getId(), entity);
	}

	@Override
	public void remove(CustomerEntity entity) {
		mapCustomers.remove(entity);
	}

	@Override
	public CustomerEntity findById(Long id) {
		return mapCustomers.get(id);
	}

	@Override
	public Collection<CustomerEntity> findAll() {
		return mapCustomers.values();
	}
}
