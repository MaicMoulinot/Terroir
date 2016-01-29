package com.jomm.terroir.dao;

import javax.ejb.Stateless;

import com.jomm.terroir.business.Customer;

/**
 * This Class defines all CRUD operations involving a {@link Customer}.
 * It implements {@link CustomerDao}, and it extends {@link GenericDao} using a {@link Customer} parameter.
 * @author Maic
 */
@Stateless
public class CustomerDaoJpa extends GenericDao<Customer> implements CustomerDao {
	
}
