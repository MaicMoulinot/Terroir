package com.jomm.terroir.dao;

import javax.ejb.Stateless;

import com.jomm.terroir.business.model.Product;

/**
 * This Class defines all CRUD operations involving a {@link Product}.
 * It implements {@link DaoProduct}, and it extends {@link DaoGenericJpa} using a {@link Product} parameter.
 * @author Maic
 */
@Stateless
public class DaoProductJpa extends DaoGenericJpa<Product> implements DaoProduct {
	
}
