package com.jomm.terroir.dao;

import javax.ejb.Stateless;

import com.jomm.terroir.business.CustomerEntity;

@Stateless
public class CustomerDaoJpa extends GenericDao<CustomerEntity> implements CustomerDao {
	
}
