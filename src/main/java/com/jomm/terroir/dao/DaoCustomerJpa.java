package com.jomm.terroir.dao;

import javax.ejb.Stateless;

import com.jomm.terroir.business.model.Customer;

/**
 * This Class defines all CRUD operations involving a {@link Customer}.
 * It implements {@link DaoCustomer}, and it extends {@link DaoGenericJpa} using a {@link Customer} parameter.
 * @author Maic
 */
@Stateless
public class DaoCustomerJpa extends DaoGenericJpa<Customer> implements DaoCustomer {
	
}
