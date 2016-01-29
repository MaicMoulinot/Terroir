package com.jomm.terroir.dao;

import javax.ejb.Stateless;

import com.jomm.terroir.business.ProductEntity;

@Stateless
public class ProductDaoJpa extends GenericDao<ProductEntity> implements ProductDao {
	
}
