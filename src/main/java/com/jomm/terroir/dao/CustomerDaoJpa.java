package com.jomm.terroir.dao;

import javax.ejb.Stateless;

import com.jomm.terroir.business.Customer;

@Stateless
public class CustomerDaoJpa extends GenericDao<Customer> implements CustomerDao {
	
}
