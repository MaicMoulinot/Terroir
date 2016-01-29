package com.jomm.terroir.dao;

import javax.ejb.Stateless;

import com.jomm.terroir.business.Product;

/**
 * This Class defines all CRUD operations involving a {@link Product}.
 * It implements {@link ProductDao}, and it extends {@link GenericDao} using a {@link Product} parameter.
 * @author Maic
 */
@Stateless
public class ProductDaoJpa extends GenericDao<Product> implements ProductDao {
	
}
