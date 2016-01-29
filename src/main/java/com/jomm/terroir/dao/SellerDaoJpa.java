package com.jomm.terroir.dao;

import javax.ejb.Stateless;

import com.jomm.terroir.business.Seller;

/**
 * This Class defines all CRUD operations involving a {@link Seller}.
 * It implements {@link SellerDao}, and it extends {@link GenericDao} using a {@link Seller} parameter.
 * @author Maic
 */
@Stateless
public class SellerDaoJpa extends GenericDao<Seller> implements SellerDao {
	
}
