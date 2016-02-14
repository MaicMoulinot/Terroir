package com.jomm.terroir.dao;

import javax.ejb.Stateless;

import com.jomm.terroir.business.model.Seller;

/**
 * This Class defines all CRUD operations involving a {@link Seller}.
 * It implements {@link DaoSeller}, and it extends {@link DaoGenericJpa} using a {@link Seller} parameter.
 * @author Maic
 */
@Stateless
public class DaoSellerJpa extends DaoGenericJpa<Seller> implements DaoSeller {
	
}
