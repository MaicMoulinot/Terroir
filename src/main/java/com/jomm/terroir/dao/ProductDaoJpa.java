package com.jomm.terroir.dao;

import javax.ejb.Stateless;

import com.jomm.terroir.business.Product;

@Stateless
public class ProductDaoJpa extends GenericDao<Product> implements ProductDao {
	
}
